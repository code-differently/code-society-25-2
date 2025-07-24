import { auth, clerkClient } from '@clerk/nextjs/server';
import { NextResponse } from 'next/server';

/**
 * Get session token for MCP authentication
 */
export async function GET() {
  console.log('[MCP Auth] GET /api/mcp/session called');

  try {
    const { userId, sessionId, getToken } = await auth();

    if (!userId || !sessionId) {
      console.log('[MCP Auth] No active session found');
      return new Response('Unauthorized - No active session', { status: 401 });
    }

    // Get the session token
    const sessionToken = await getToken();

    if (!sessionToken) {
      console.log('[MCP Auth] Could not retrieve session token');
      return new Response('Unauthorized - Could not retrieve session token', {
        status: 401,
      });
    }

    console.log(`[MCP Auth] Session token generated for user: ${userId}`);

    // Get user details for display
    const client = await clerkClient();
    const user = await client.users.getUser(userId);

    return NextResponse.json({
      sessionToken,
      userId,
      sessionId,
      user: {
        email: user.emailAddresses[0]?.emailAddress,
        firstName: user.firstName,
        lastName: user.lastName,
      },
      instructions: {
        usage: 'Copy the sessionToken and use it with the MCP login tool',
        example: `login with sessionToken: "${sessionToken.substring(0, 20)}..."`,
      },
    });
  } catch (error) {
    console.error('[MCP Auth] Error generating session token:', error);
    return new Response('Internal Server Error', { status: 500 });
  }
}
