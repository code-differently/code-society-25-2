# MCP Server Setup for fullstack_demo

This document explains how to use the Model Context Protocol (MCP) server that has been integrated into the fullstack_demo application. This allows AI agents like Claude Desktop to securely interact with your todos through a standardized protocol.

## What is MCP?

Model Context Protocol (MCP) is a standardized way for AI assistants to securely connect to external tools and services. This implementation allows Claude Desktop and other MCP-compatible AI agents to:
- View all your todos
- Create new todos
- Update existing todos
- Delete todos
- Access your Clerk user data

All operations are authenticated using Clerk OAuth tokens, ensuring secure access to your data.

## Architecture

The MCP server is built using:
- **@vercel/mcp-adapter**: Handles the core MCP protocol logic
- **@clerk/mcp-tools**: Integrates Clerk OAuth authentication with MCP
- **Next.js App Router**: Provides the API endpoints

### Key Components

1. **MCP Route Handler** (`src/app/[transport]/route.ts`)
   - Defines MCP tools for todo operations
   - Supports both `/mcp` (HTTP) and `/sse` (Server-Sent Events) transports
   - Authenticates requests using Clerk OAuth tokens

2. **OAuth Metadata Endpoints**
   - `/.well-known/oauth-protected-resource/mcp` - Resource metadata
   - `/.well-known/oauth-authorization-server` - Authorization server metadata (backward compatibility)

3. **Middleware** (`src/middleware.ts`)
   - Updated to allow public access to `.well-known` endpoints
   - Maintains authentication for all other routes

## Setup Instructions

### 1. Enable Dynamic Client Registration in Clerk

**IMPORTANT**: Before the MCP server can work, you must enable dynamic client registration in your Clerk Dashboard.

1. Go to the [Clerk Dashboard](https://dashboard.clerk.com/)
2. Navigate to **Configure** → **OAuth Applications**
3. Toggle on **Dynamic client registration**

This allows MCP-compatible clients to automatically register themselves during the OAuth flow.

### 2. Start the Development Server

```bash
cd /Users/anthonymays/source/forks/code-society-25-2/lib/javascript/fullstack_demo
npm run dev
```

The server will start on `http://localhost:3000` (or your configured port).

### 3. Connect AI Agents to Your MCP Server

#### Recommended Method: Using mcp-remote

The easiest way to connect AI agents to your MCP server is using the `mcp-remote` utility:

```bash
npx -y mcp-remote http://localhost:3000/mcp
```

This command:
- Automatically configures the MCP client
- Handles OAuth authentication with Clerk
- Works with any MCP-compatible AI agent
- Requires no manual configuration file editing

#### Alternative Method: Manual Configuration

If you prefer to manually configure your AI agent:

1. Locate your Claude Desktop configuration file:
   - **macOS**: `~/Library/Application Support/Claude/claude_desktop_config.json`
   - **Windows**: `%APPDATA%\Claude\claude_desktop_config.json`

2. Add your MCP server configuration:

```json
{
  "mcpServers": {
    "fullstack_demo_todos": {
      "url": "http://localhost:3000/mcp",
      "transport": "streamable-http",
      "auth": {
        "type": "oauth2",
        "authorizationUrl": "http://localhost:3000/.well-known/oauth-authorization-server"
      }
    }
  }
}
```

**Note**: The `tokenUrl` is automatically discovered from the authorization server metadata.

3. Restart your AI agent

4. When the agent tries to use the MCP server, you'll be prompted to authenticate via Clerk OAuth.

### 4. Production Deployment

When deploying to production:

1. Connect to your production MCP server using `mcp-remote`:
   ```bash
   npx -y mcp-remote https://your-production-domain.com/mcp
   ```

   Or update your AI agent's configuration manually:
   ```json
   {
     "mcpServers": {
       "fullstack_demo_todos": {
         "url": "https://your-production-domain.com/mcp",
         "transport": "streamable-http",
         "auth": {
           "type": "oauth2",
           "authorizationUrl": "https://your-production-domain.com/.well-known/oauth-authorization-server"
         }
       }
     }
   }
   ```

2. Ensure your production environment has all required environment variables set:
   - `NEXT_PUBLIC_CLERK_PUBLISHABLE_KEY`
   - `CLERK_SECRET_KEY`
   - Database connection strings
   - Any other app-specific variables

## Available MCP Tools

### 1. `get-todos`
Gets all todos for the authenticated user.

**Parameters**: None

**Example Response**:
```json
[
  {
    "id": 1,
    "text": "Buy groceries",
    "completed": false
  },
  {
    "id": 2,
    "text": "Finish homework",
    "completed": true
  }
]
```

### 2. `create-todo`
Creates a new todo for the authenticated user.

**Parameters**:
- `text` (string, required): The text content of the todo
- `completed` (boolean, optional): Whether the todo is completed (defaults to false)

**Example Response**:
```json
{
  "id": 1234567890,
  "text": "New todo item",
  "completed": false
}
```

### 3. `update-todo`
Updates an existing todo for the authenticated user.

**Parameters**:
- `id` (number, required): The ID of the todo to update
- `text` (string, optional): The new text content of the todo
- `completed` (boolean, optional): Whether the todo is completed

**Example Response**:
```json
{
  "id": 1234567890,
  "text": "Updated todo text",
  "completed": true
}
```

### 4. `delete-todo`
Deletes a todo for the authenticated user.

**Parameters**:
- `id` (number, required): The ID of the todo to delete

**Example Response**:
```json
{
  "success": true,
  "deletedId": 1234567890
}
```

### 5. `get-clerk-user-data`
Gets data about the Clerk user that authorized the request.

**Parameters**: None

**Example Response**:
```json
{
  "id": "user_xxxxxxxxxxxxx",
  "firstName": "John",
  "lastName": "Doe",
  "emailAddresses": [...],
  ...
}
```

## Using the MCP Server with Claude

Once configured, you can interact with your todos naturally through Claude:

**Example Prompts**:
- "Show me all my todos"
- "Create a new todo to buy milk"
- "Mark todo #123 as completed"
- "Delete the todo about buying groceries"
- "What todos do I have pending?"

Claude will use the appropriate MCP tools to interact with your todo list securely.

## Security

- All MCP requests are authenticated using Clerk OAuth tokens
- The middleware ensures `.well-known` endpoints are publicly accessible for OAuth discovery
- All other routes remain protected by Clerk authentication
- Each user can only access their own todos (enforced by the todoRepository)

## Troubleshooting

### MCP Server Not Connecting

1. Verify the development server is running
2. Check that dynamic client registration is enabled in Clerk Dashboard
3. Ensure the URLs in Claude Desktop config match your server URLs
4. Check browser/Claude console for error messages

### Authentication Errors

1. Verify your Clerk API keys are set correctly in `.env`
2. Ensure the Clerk Frontend API URL in Claude config is correct
3. Try re-authenticating by disconnecting and reconnecting the MCP server in Claude

### Tool Execution Errors

1. Check the Next.js server logs for detailed error messages
2. Verify the database is accessible and properly configured
3. Ensure the authenticated user has permission to perform the action

## Files Created/Modified

### New Files
- `src/app/[transport]/route.ts` - Main MCP server handler
- `src/app/.well-known/oauth-protected-resource/mcp/route.ts` - OAuth resource metadata
- `src/app/.well-known/oauth-authorization-server/route.ts` - OAuth server metadata

### Modified Files
- `src/middleware.ts` - Updated to allow public access to `.well-known` endpoints
- `package.json` - Added MCP dependencies

### Dependencies Added
- `@vercel/mcp-adapter` - MCP protocol handler
- `@clerk/mcp-tools` - Clerk OAuth integration for MCP

## Additional Resources

- [Clerk MCP Documentation](https://clerk.com/docs/nextjs/guides/development/mcp/build-mcp-server)
- [MCP Specification](https://modelcontextprotocol.io/)
- [Clerk OAuth Documentation](https://clerk.com/docs/guides/development/verifying-oauth-access-tokens)
- [Vercel MCP Adapter](https://github.com/vercel/mcp-adapter)
- [Example Repository](https://github.com/clerk/mcp-nextjs-example)
