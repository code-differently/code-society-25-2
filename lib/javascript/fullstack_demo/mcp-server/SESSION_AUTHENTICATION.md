# Session-Based Authentication Setup for Claude Desktop

## Quick Setup Guide

This MCP server now uses **session-based authentication** with Clerk instead of API keys. Users can log into the web app and use their session tokens for secure, personalized access.

### 1. Configure Your Todo App

Add these environment variables to your todo app's `.env` file:

```bash
# Add to /lib/javascript/fullstack_demo/.env
CLERK_SECRET_KEY=your-clerk-secret-key-here
```

**Your Clerk secret key can be found in your Clerk Dashboard.**

### 2. Configure MCP Server

Create/update the MCP server's `.env` file:

```bash
# /lib/javascript/fullstack_demo/mcp-server/.env
TODO_API_BASE_URL=http://localhost:3000/api/mcp
CLERK_SECRET_KEY=your-clerk-secret-key-here
```

**Use the same Clerk secret key in both files!**

### 3. Configure Claude Desktop

Update your Claude Desktop config file:

**Location:** `~/Library/Application Support/Claude/claude_desktop_config.json` (macOS)

```json
{
  "mcpServers": {
    "todo-mcp-server": {
      "command": "node",
      "args": ["/full/path/to/your/mcp-server/dist/index.js"],
      "env": {
        "TODO_API_BASE_URL": "http://localhost:3000/api/mcp",
        "CLERK_SECRET_KEY": "your-clerk-secret-key-here"
      }
    }
  }
}
```

### 4. Build and Test

1. **Start your todo app:**
   ```bash
   cd /lib/javascript/fullstack_demo
   npm run dev
   ```

2. **Build the MCP server:**
   ```bash
   cd /lib/javascript/fullstack_demo/mcp-server
   npm run build
   ```

3. **Restart Claude Desktop** to pick up the new configuration

## How to Use Session Authentication

### Step 1: Get Your Session Token

1. **Open the todo app** in your browser: `http://localhost:3000`
2. **Sign in** with your Clerk account
3. **Get your session token** using one of these methods:

   **Method A: API Endpoint (Easiest)**
   - Visit: `http://localhost:3000/api/mcp/session`
   - Copy the `sessionToken` from the response

   **Method B: Browser DevTools**
   - Open Developer Tools (F12)
   - Go to Application/Storage tab
   - Find the `__session` cookie and copy its value

### Step 2: Login in Claude Desktop

In Claude Desktop, use the login tool:

```
Please login to the todo app using my session token: sess_2h9j8k7l6m5n4o3p2q1r0s9t8u7v6w5x4y3z
```

### Step 3: Start Managing Todos

Once logged in, you can use all todo commands:

```
Can you show me my todos?
Create a todo: "Buy groceries"
Mark todo 1 as completed
Get my todo statistics
```

## Available Tools

### Authentication Tools
- `login` - Login with your session token
- `logout` - Logout and clear your session
- `get_auth_status` - Check your current authentication status
- `get_login_url` - Get instructions for logging in

### Todo Management Tools
- `get_todos` - Retrieve all your todos
- `create_todo` - Create a new todo item
- `update_todo` - Update an existing todo
- `delete_todo` - Delete a todo item
- `toggle_todo` - Toggle completion status
- `get_todo_stats` - Get statistics about your todos

## Troubleshooting

### "Authentication required. Please use the login tool first."
- You need to login first using your session token
- Get a fresh session token from the web app
- Use the `login` tool with your session token

### "Login failed. Invalid session token or session has expired."
- Your session token may have expired
- Get a new session token from the web app
- Make sure you copied the complete token

### "Cannot connect to API"
- Verify your todo app is running on the correct port
- Check that the `TODO_API_BASE_URL` points to the MCP endpoints (`/api/mcp`)
- Ensure firewalls aren't blocking the connection

### Session expires quickly
- Session tokens have built-in expiration for security
- Simply get a new token when yours expires
- The MCP server will remember your login until you restart it

## Security Notes

✅ **Improved Security:**

1. **User-specific access** - Each user only sees their own todos
2. **Session-based auth** - More secure than shared API keys
3. **Automatic expiration** - Sessions expire automatically
4. **No shared secrets** - Each user has their own session
5. **Clerk integration** - Benefits from Clerk's security features

## Multiple Users

Each Claude Desktop user can:
1. Sign into the web app with their own account
2. Get their own session token
3. Access only their own todos
4. Have independent authentication status

This provides true multi-user support with proper data isolation!
