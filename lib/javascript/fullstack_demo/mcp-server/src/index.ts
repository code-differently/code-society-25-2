#!/usr/bin/env node

import { Server } from '@modelcontextprotocol/sdk/server/index.js';
import { StdioServerTransport } from '@modelcontextprotocol/sdk/server/stdio.js';
import {
  CallToolRequest,
  CallToolRequestSchema,
  ListToolsRequestSchema,
} from '@modelcontextprotocol/sdk/types.js';
import dotenv from 'dotenv';
import { TodoApiClient } from './api-client.js';
import { SessionManager, UserSession } from './auth/session-manager.js';
import { CreateTodoRequest, Todo, UpdateTodoRequest } from './types/todo.js';

// Load environment variables
dotenv.config();

class TodoMCPServer {
  private server: Server;
  private apiClient: TodoApiClient;
  private sessionManager: SessionManager;

  constructor() {
    const apiBaseUrl =
      process.env.TODO_API_BASE_URL || 'http://localhost:3000/api/mcp';

    this.apiClient = new TodoApiClient(apiBaseUrl);
    this.sessionManager = new SessionManager();

    this.server = new Server(
      {
        name: 'todo-mcp-server',
        version: '1.0.0',
      },
      {
        capabilities: {
          tools: {},
        },
      },
    );

    this.setupToolHandlers();
    this.setupErrorHandling();

    // Initialize with existing session if available
    this.initializeSession();
  }

  /**
   * Initialize with existing session on startup
   */
  private async initializeSession(): Promise<void> {
    try {
      const session = await this.sessionManager.getCurrentSession();
      if (session) {
        this.apiClient.setSessionToken(session.sessionToken);
        console.debug(`[MCP] Restored session for user: ${session.email}`);
      }
    } catch (error) {
      console.debug('[MCP] No existing session to restore');
    }
  }

  private setupErrorHandling(): void {
    this.server.onerror = (error: Error) => {
      console.debug('[MCP Error]', error);
    };

    process.on('SIGINT', async () => {
      await this.server.close();
      process.exit(0);
    });
  }

  private setupToolHandlers(): void {
    this.server.setRequestHandler(ListToolsRequestSchema, async () => {
      return {
        tools: [
          {
            name: 'get_todos',
            description: 'Retrieve all todos for the authenticated user',
            inputSchema: {
              type: 'object',
              properties: {},
              required: [],
            },
          },
          {
            name: 'create_todo',
            description: 'Create a new todo item',
            inputSchema: {
              type: 'object',
              properties: {
                text: {
                  type: 'string',
                  description: 'The text content of the todo item',
                },
                completed: {
                  type: 'boolean',
                  description: 'Whether the todo is completed (default: false)',
                  default: false,
                },
              },
              required: ['text'],
            },
          },
          {
            name: 'update_todo',
            description: 'Update an existing todo item',
            inputSchema: {
              type: 'object',
              properties: {
                id: {
                  type: 'number',
                  description: 'The ID of the todo to update',
                },
                text: {
                  type: 'string',
                  description: 'The new text content of the todo item',
                },
                completed: {
                  type: 'boolean',
                  description: 'Whether the todo is completed',
                },
              },
              required: ['id'],
            },
          },
          {
            name: 'delete_todo',
            description: 'Delete a todo item',
            inputSchema: {
              type: 'object',
              properties: {
                id: {
                  type: 'number',
                  description: 'The ID of the todo to delete',
                },
              },
              required: ['id'],
            },
          },
          {
            name: 'toggle_todo',
            description: 'Toggle the completion status of a todo item',
            inputSchema: {
              type: 'object',
              properties: {
                id: {
                  type: 'number',
                  description: 'The ID of the todo to toggle',
                },
              },
              required: ['id'],
            },
          },
          {
            name: 'get_todo_stats',
            description:
              'Get statistics about todos (total, completed, pending)',
            inputSchema: {
              type: 'object',
              properties: {},
              required: [],
            },
          },
          {
            name: 'login',
            description:
              'Login with your session token to authenticate with the todo app',
            inputSchema: {
              type: 'object',
              properties: {
                sessionToken: {
                  type: 'string',
                  description: 'Your Clerk session token from the todo app',
                },
              },
              required: ['sessionToken'],
            },
          },
          {
            name: 'logout',
            description: 'Logout and clear your session',
            inputSchema: {
              type: 'object',
              properties: {},
              required: [],
            },
          },
          {
            name: 'get_auth_status',
            description: 'Check your current authentication status',
            inputSchema: {
              type: 'object',
              properties: {},
              required: [],
            },
          },
          {
            name: 'get_login_url',
            description:
              'Get the URL to login to the todo app and obtain a session token',
            inputSchema: {
              type: 'object',
              properties: {},
              required: [],
            },
          },
        ],
      };
    });

    this.server.setRequestHandler(
      CallToolRequestSchema,
      async (request: CallToolRequest) => {
        const { name, arguments: args } = request.params;

        try {
          switch (name) {
            case 'login':
              if (
                !args ||
                typeof args !== 'object' ||
                !('sessionToken' in args)
              ) {
                throw new Error('Missing required parameter: sessionToken');
              }
              return await this.login(args.sessionToken as string);

            case 'logout':
              return await this.logout();

            case 'get_auth_status':
              return await this.getAuthStatus();

            case 'get_login_url':
              return await this.getLoginUrl();

            case 'get_todos':
              return await this.getTodos();

            case 'create_todo':
              if (!args || typeof args !== 'object' || !('text' in args)) {
                throw new Error('Missing required parameter: text');
              }
              return await this.createTodo(
                args as unknown as CreateTodoRequest,
              );

            case 'update_todo':
              if (!args || typeof args !== 'object' || !('id' in args)) {
                throw new Error('Missing required parameter: id');
              }
              return await this.updateTodo(
                args as unknown as UpdateTodoRequest,
              );

            case 'delete_todo':
              if (!args || typeof args !== 'object' || !('id' in args)) {
                throw new Error('Missing required parameter: id');
              }
              return await this.deleteTodo(args.id as number);

            case 'toggle_todo':
              if (!args || typeof args !== 'object' || !('id' in args)) {
                throw new Error('Missing required parameter: id');
              }
              return await this.toggleTodo(args.id as number);

            case 'get_todo_stats':
              return await this.getTodoStats();

            default:
              throw new Error(`Unknown tool: ${name}`);
          }
        } catch (error) {
          return {
            content: [
              {
                type: 'text',
                text: `Error: ${error instanceof Error ? error.message : String(error)}`,
              },
            ],
            isError: true,
          };
        }
      },
    );
  }

  private async getTodos() {
    const session = await this.requireAuth();
    console.debug(`[MCP] Getting todos for user: ${session.email}...`);

    const todos = await this.apiClient.getTodos();
    console.debug(`[MCP] Retrieved ${todos.length} todos`);

    return {
      content: [
        {
          type: 'text',
          text: JSON.stringify(todos, null, 2),
        },
      ],
    };
  }

  private async createTodo(args: CreateTodoRequest) {
    const session = await this.requireAuth();
    console.debug(
      `[MCP] Creating todo for user ${session.email}: "${args.text}"`,
    );

    const result = await this.apiClient.createTodo(args.text, args.completed);
    console.debug(`[MCP] Todo created with ID: ${result}`);

    return {
      content: [
        {
          type: 'text',
          text: `Todo created successfully with ID: ${result}`,
        },
      ],
    };
  }

  private async updateTodo(args: UpdateTodoRequest) {
    const session = await this.requireAuth();

    const updates: Partial<Omit<Todo, 'id'>> = {};

    if (args.text !== undefined) {
      updates.text = args.text;
    }

    if (args.completed !== undefined) {
      updates.completed = args.completed;
    }

    console.debug(`[MCP] Updating todo ${args.id} for user ${session.email}`);
    const result = await this.apiClient.updateTodo(args.id, updates);

    return {
      content: [
        {
          type: 'text',
          text: `Todo updated successfully: ${JSON.stringify(result, null, 2)}`,
        },
      ],
    };
  }

  private async deleteTodo(id: number) {
    const session = await this.requireAuth();
    console.debug(`[MCP] Deleting todo ${id} for user ${session.email}`);

    await this.apiClient.deleteTodo(id);

    return {
      content: [
        {
          type: 'text',
          text: `Todo with ID ${id} deleted successfully`,
        },
      ],
    };
  }

  private async toggleTodo(id: number) {
    const session = await this.requireAuth();
    console.debug(`[MCP] Toggling todo ${id} for user ${session.email}`);

    const result = await this.apiClient.toggleTodo(id);

    return {
      content: [
        {
          type: 'text',
          text: `Todo toggled successfully: ${JSON.stringify(result, null, 2)}`,
        },
      ],
    };
  }

  private async getTodoStats() {
    const session = await this.requireAuth();
    console.debug(`[MCP] Getting todo stats for user ${session.email}`);

    const stats = await this.apiClient.getTodoStats();

    return {
      content: [
        {
          type: 'text',
          text: JSON.stringify(stats, null, 2),
        },
      ],
    };
  }

  /**
   * Check if user is authenticated and get session
   */
  private async getActiveSession(): Promise<UserSession | null> {
    return await this.sessionManager.getCurrentSession();
  }

  /**
   * Require authentication for protected operations
   */
  private async requireAuth(): Promise<UserSession> {
    const session = await this.getActiveSession();
    if (!session) {
      throw new Error(
        'Authentication required. Please use the "login" tool first.',
      );
    }
    return session;
  }

  /**
   * Login with session token
   */
  private async login(sessionToken: string) {
    try {
      const session = await this.sessionManager.storeSession(sessionToken);

      if (!session) {
        return {
          content: [
            {
              type: 'text',
              text: 'Login failed. Invalid session token or session has expired.',
            },
          ],
          isError: true,
        };
      }

      // Set session token on API client for immediate use
      this.apiClient.setSessionToken(sessionToken);

      return {
        content: [
          {
            type: 'text',
            text: `Successfully logged in as ${session.firstName} ${session.lastName} (${session.email})

You can now use all todo management commands:
- get_todos
- create_todo
- update_todo
- delete_todo
- toggle_todo
- get_todo_stats`,
          },
        ],
      };
    } catch (error) {
      return {
        content: [
          {
            type: 'text',
            text: `Login failed: ${error instanceof Error ? error.message : String(error)}`,
          },
        ],
        isError: true,
      };
    }
  }

  /**
   * Logout current user
   */
  private async logout() {
    try {
      const session = await this.getActiveSession();

      if (!session) {
        return {
          content: [
            {
              type: 'text',
              text: 'No active session found. You are already logged out.',
            },
          ],
        };
      }

      await this.sessionManager.removeSession(session.userId);

      // Clear session token from API client
      this.apiClient.clearSessionToken();

      return {
        content: [
          {
            type: 'text',
            text: `Successfully logged out ${session.firstName} ${session.lastName}`,
          },
        ],
      };
    } catch (error) {
      return {
        content: [
          {
            type: 'text',
            text: `Logout failed: ${error instanceof Error ? error.message : String(error)}`,
          },
        ],
        isError: true,
      };
    }
  }

  /**
   * Get authentication status
   */
  private async getAuthStatus() {
    try {
      const session = await this.getActiveSession();

      if (!session) {
        return {
          content: [
            {
              type: 'text',
              text: JSON.stringify(
                {
                  authenticated: false,
                  message:
                    'Not logged in. Use the "login" tool with your session token.',
                },
                null,
                2,
              ),
            },
          ],
        };
      }

      const status = {
        authenticated: true,
        userId: session.userId,
        email: session.email,
        name: `${session.firstName} ${session.lastName}`.trim(),
        expiresAt: session.expiresAt,
        sessionAge: Math.floor(
          (new Date().getTime() - new Date(session.createdAt).getTime()) /
            1000 /
            60,
        ), // minutes
      };

      return {
        content: [
          {
            type: 'text',
            text: JSON.stringify(status, null, 2),
          },
        ],
      };
    } catch (error) {
      return {
        content: [
          {
            type: 'text',
            text: `Error checking auth status: ${error instanceof Error ? error.message : String(error)}`,
          },
        ],
        isError: true,
      };
    }
  }

  /**
   * Get login URL and instructions
   */
  private async getLoginUrl() {
    const baseUrl =
      process.env.TODO_API_BASE_URL?.replace('/api/mcp', '') ||
      'http://localhost:3000';

    return {
      content: [
        {
          type: 'text',
          text: `To login to the Todo app:

**Method 1: Using the Session API (Recommended)**
1. Open your browser and go to: ${baseUrl}
2. Sign in with your Clerk account
3. Visit: ${baseUrl}/api/mcp/session
4. Copy the sessionToken from the response
5. Use the "login" tool with that token

**Method 2: Using Browser DevTools**
1. Open your browser and go to: ${baseUrl}
2. Sign in with your Clerk account
3. Open the browser's developer tools (F12)
4. Go to the Application/Storage tab
5. Find the "__session" cookie and copy its value
6. Use the "login" tool with that session token

**Example:**
login with sessionToken: "sess_2h..."

The session token will look like: sess_xxxxxxxxxxxxxxxxxxxxxxxxxx

After logging in successfully, you'll be able to use all todo management tools with your personal account.`,
        },
      ],
    };
  }

  async run(): Promise<void> {
    const transport = new StdioServerTransport();
    await this.server.connect(transport);
    console.log('Todo MCP server running on stdio');
    console.debug(
      `API Base URL: ${process.env.TODO_API_BASE_URL || 'http://localhost:3000/api/mcp'}`,
    );
    console.debug(
      `Clerk Secret Key configured: ${process.env.CLERK_SECRET_KEY ? 'Yes' : 'No'}`,
    );

    // Check if user has an active session
    try {
      const session = await this.getActiveSession();
      if (session) {
        console.debug(`Active session found for: ${session.email}`);
      } else {
        console.debug('No active session. User will need to login.');
      }
    } catch (error) {
      console.debug('Session check failed');
    }
  }
}

const server = new TodoMCPServer();
server.run().catch(console.error);
