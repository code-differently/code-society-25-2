import { createClerkClient } from '@clerk/clerk-sdk-node';
import fs from 'fs/promises';
import os from 'os';
import path from 'path';

export interface UserSession {
  userId: string;
  sessionToken: string;
  email?: string;
  firstName?: string;
  lastName?: string;
  expiresAt: string;
  createdAt: string;
}

export class SessionManager {
  private sessionsFile: string;
  private clerk: ReturnType<typeof createClerkClient> | null = null;

  constructor() {
    // Store sessions in a file in the user's home directory
    const homeDir = process.env.HOME || process.env.USERPROFILE || os.homedir();
    this.sessionsFile = path.join(homeDir, '.mcp-todo-sessions.json');

    console.log(`[Session Manager] Sessions file path: ${this.sessionsFile}`);

    // Initialize Clerk if secret key is available
    if (process.env.CLERK_SECRET_KEY) {
      this.clerk = createClerkClient({
        secretKey: process.env.CLERK_SECRET_KEY,
      });
    }
  }

  /**
   * Load sessions from file
   */
  private async loadSessions(): Promise<Record<string, UserSession>> {
    try {
      const data = await fs.readFile(this.sessionsFile, 'utf8');
      return JSON.parse(data);
    } catch (error) {
      // File doesn't exist or is invalid, return empty object
      return {};
    }
  }

  /**
   * Save sessions to file
   */
  private async saveSessions(
    sessions: Record<string, UserSession>,
  ): Promise<void> {
    await fs.writeFile(this.sessionsFile, JSON.stringify(sessions, null, 2));
  }

  /**
   * Store a user session
   */
  async storeSession(sessionToken: string): Promise<UserSession | null> {
    if (!this.clerk) {
      throw new Error(
        'Clerk is not initialized. Please provide CLERK_SECRET_KEY.',
      );
    }

    try {
      const decodedToken = await this.clerk.verifyToken(sessionToken);

      if (!decodedToken.sid) {
        console.warn(
          '[Session Manager] Invalid session token, no session ID found.',
        );
        return null;
      }

      // Get the actual session using the session ID from the token
      const sessionData = await this.clerk.sessions.getSession(
        decodedToken.sid,
      );

      if (!sessionData || sessionData.status !== 'active') {
        console.warn(
          `[Session Manager] Session not found or inactive for token: ${sessionToken}`,
        );
        return null;
      }

      // Get user details
      const user = await this.clerk.users.getUser(sessionData.userId);

      const userSession: UserSession = {
        userId: sessionData.userId,
        sessionToken,
        email: user.emailAddresses[0]?.emailAddress,
        firstName: user.firstName || '',
        lastName: user.lastName || '',
        expiresAt: new Date(sessionData.expireAt).toISOString(),
        createdAt: new Date().toISOString(),
      };

      // Load existing sessions
      const sessions = await this.loadSessions();

      // Store session with userId as key
      sessions[sessionData.userId] = userSession;

      // Clean up expired sessions
      await this.cleanupExpiredSessions(sessions);

      // Save updated sessions
      await this.saveSessions(sessions);

      console.log(
        `[Session Manager] Stored session for user: ${sessionData.userId}`,
      );
      return userSession;
    } catch (error) {
      console.error('[Session Manager] Error storing session:', error);
      return null;
    }
  }

  /**
   * Get active session for current user (latest session)
   */
  async getCurrentSession(): Promise<UserSession | null> {
    const sessions = await this.loadSessions();

    // Get all non-expired sessions
    const activeSessions = Object.values(sessions).filter(
      (session) => new Date(session.expiresAt) > new Date(),
    );

    if (activeSessions.length === 0) {
      return null;
    }

    // Return the most recent session
    const latestSession = activeSessions.sort(
      (a, b) =>
        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime(),
    )[0];

    // Validate session is still active with Clerk
    if (this.clerk) {
      try {
        // Extract session ID from the stored token
        const decodedToken = JSON.parse(
          Buffer.from(
            latestSession.sessionToken.split('.')[1],
            'base64',
          ).toString(),
        );

        if (!decodedToken.sid) {
          await this.removeSession(latestSession.userId);
          return null;
        }

        const session = await this.clerk.sessions.getSession(decodedToken.sid);
        if (!session || session.status !== 'active') {
          await this.removeSession(latestSession.userId);
          return null;
        }
      } catch (error) {
        console.debug('[Session Manager] Session validation failed:', error);
        await this.removeSession(latestSession.userId);
        return null;
      }
    }

    return latestSession;
  }

  /**
   * Remove a user session
   */
  async removeSession(userId: string): Promise<void> {
    const sessions = await this.loadSessions();
    delete sessions[userId];
    await this.saveSessions(sessions);
    console.log(`[Session Manager] Removed session for user: ${userId}`);
  }

  /**
   * Remove all sessions (logout all users)
   */
  async clearAllSessions(): Promise<void> {
    await this.saveSessions({});
    console.log('[Session Manager] Cleared all sessions');
  }

  /**
   * Clean up expired sessions
   */
  private async cleanupExpiredSessions(
    sessions: Record<string, UserSession>,
  ): Promise<void> {
    const now = new Date();
    let cleaned = false;

    for (const [userId, session] of Object.entries(sessions)) {
      if (new Date(session.expiresAt) <= now) {
        delete sessions[userId];
        cleaned = true;
      }
    }

    if (cleaned) {
      console.log('[Session Manager] Cleaned up expired sessions');
    }
  }

  /**
   * List all active sessions
   */
  async listActiveSessions(): Promise<UserSession[]> {
    const sessions = await this.loadSessions();
    const now = new Date();

    return Object.values(sessions).filter(
      (session) => new Date(session.expiresAt) > now,
    );
  }
}
