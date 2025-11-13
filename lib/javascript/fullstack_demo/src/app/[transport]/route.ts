import { Todo } from '@/models';
import { createTodoRepository } from '@/repositories';
import { verifyClerkToken } from '@clerk/mcp-tools/next';
import { auth, clerkClient } from '@clerk/nextjs/server';
import { createMcpHandler, withMcpAuth } from '@vercel/mcp-adapter';
import { z } from 'zod';

const clerk = await clerkClient();
const todoRepository = createTodoRepository();

const handler = createMcpHandler((server) => {
  // Get all todos for the authenticated user
  server.tool(
    'get-todos',
    'Gets all todos for the authenticated user',
    {},
    async (_, extra) => {
      const userId = extra?.authInfo?.extra?.userId as string;
      const todos = await todoRepository.getAll(userId);

      return {
        content: [
          {
            type: 'text',
            text: JSON.stringify(todos, null, 2),
          },
        ],
      };
    },
  );

  // Create a new todo
  server.tool(
    'create-todo',
    'Creates a new todo for the authenticated user',
    {
      text: z.string().describe('The text content of the todo'),
      completed: z
        .boolean()
        .optional()
        .describe(
          'Whether the todo is completed (optional, defaults to false)',
        ),
    },
    async (args, extra) => {
      const userId = extra?.authInfo?.extra?.userId as string;
      const todo: Todo = {
        id: Date.now(),
        text: args.text as string,
        completed: (args.completed as boolean) || false,
      };

      const id = await todoRepository.create(todo, userId);

      return {
        content: [
          {
            type: 'text',
            text: JSON.stringify({ ...todo, id }, null, 2),
          },
        ],
      };
    },
  );

  // Update a todo
  server.tool(
    'update-todo',
    'Updates an existing todo for the authenticated user',
    {
      id: z.number().int().describe('The ID of the todo to update'),
      text: z
        .string()
        .optional()
        .describe('The new text content of the todo (optional)'),
      completed: z
        .boolean()
        .optional()
        .describe('Whether the todo is completed (optional)'),
    },
    async (args, extra) => {
      const userId = extra?.authInfo?.extra?.userId as string;
      const updates: Partial<Todo> = { id: args.id as number };

      if (args.text !== undefined) {
        updates.text = args.text as string;
      }
      if (args.completed !== undefined) {
        updates.completed = args.completed as boolean;
      }

      const updatedTodo = await todoRepository.patch(updates, userId);

      return {
        content: [
          {
            type: 'text',
            text: JSON.stringify(updatedTodo, null, 2),
          },
        ],
      };
    },
  );

  // Delete a todo
  server.tool(
    'delete-todo',
    'Deletes a todo for the authenticated user',
    {
      id: z.number().int().describe('The ID of the todo to delete'),
    },
    async (args, extra) => {
      const userId = extra?.authInfo?.extra?.userId as string;
      await todoRepository.delete(args.id as number, userId);

      return {
        content: [
          {
            type: 'text',
            text: JSON.stringify(
              { success: true, deletedId: args.id },
              null,
              2,
            ),
          },
        ],
      };
    },
  );

  // Get user data (example from Clerk guide)
  server.tool(
    'get-clerk-user-data',
    'Gets data about the Clerk user that authorized this request',
    {},
    async (_, extra) => {
      const userId = extra?.authInfo?.extra?.userId as string;
      const userData = await clerk.users.getUser(userId);

      return {
        content: [{ type: 'text', text: JSON.stringify(userData) }],
      };
    },
  );
});

const authHandler = withMcpAuth(
  handler,
  async (req, token) => {
    // For MCP OAuth flow, we need to verify the token directly
    // The route is public, so auth() won't have context
    const clerkAuth = await auth({
      acceptsToken: 'oauth_token',
    });
    return verifyClerkToken(clerkAuth, token);
  },
  {
    required: true,
    resourceMetadataPath: '/.well-known/oauth-protected-resource/mcp',
  },
);

export { authHandler as GET, authHandler as POST };
