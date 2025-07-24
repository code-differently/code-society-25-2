import { clerkClient, verifyToken } from '@clerk/nextjs/server';

/**
 * Authenticate MCP requests using session tokens
 */
export async function authenticateRequest(
  request: Request,
): Promise<string | null> {
  console.debug('[MCP Auth] Attempting session token authentication...');

  // Extract Bearer token from Authorization header
  const authHeader = request.headers.get('Authorization');
  if (!authHeader?.startsWith('Bearer ')) {
    console.debug('[MCP Auth] No Bearer token found in Authorization header');
    return null;
  }

  const sessionToken = authHeader.substring(7);
  if (!sessionToken) {
    console.debug('[MCP Auth] Empty session token');
    return null;
  }

  try {
    // Verify the session token using Clerk
    const { sid } = await verifyToken(
      request.headers.get('Authorization')?.replace('Bearer ', '') || '',
      {
        secretKey: process.env.CLERK_SECRET_KEY,
      },
    );

    // Get session by token using Clerk client
    const client = await clerkClient();
    const session = await client.sessions.getSession(sid);

    if (!session || !session.userId || session.status !== 'active') {
      console.debug('[MCP Auth] Invalid or inactive session');
      return null;
    }

    console.debug(
      `[MCP Auth] Session authentication successful for user: ${session.userId}`,
    );
    return session.userId;
  } catch (error) {
    console.debug('[MCP Auth] Session validation failed:', error);
    return null;
  }
}
