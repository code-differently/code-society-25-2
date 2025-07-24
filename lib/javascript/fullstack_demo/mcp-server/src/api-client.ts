import { Todo } from './types/todo.js';
// @ts-ignore - Using node-fetch for compatibility
import fetch from 'node-fetch';

export class TodoApiClient {
  private baseUrl: string;
  private sessionToken?: string;

  constructor(baseUrl: string, _deprecated_apiKey?: string) {
    this.baseUrl = baseUrl;
    // Note: API key parameter is deprecated in favor of session-based auth
  }

  /**
   * Set session token for authentication
   */
  setSessionToken(sessionToken: string): void {
    this.sessionToken = sessionToken;
    console.debug('[API Client] Session token updated');
  }

  /**
   * Clear session token
   */
  clearSessionToken(): void {
    this.sessionToken = undefined;
    console.debug('[API Client] Session token cleared');
  }

  /**
   * Check if client is authenticated
   */
  isAuthenticated(): boolean {
    return !!this.sessionToken;
  }

  private async makeRequest(
    endpoint: string,
    options: RequestInit = {},
  ): Promise<any> {
    const url = `${this.baseUrl}${endpoint}`;

    const headers: Record<string, string> = {
      'Content-Type': 'application/json',
    };

    // Use session token for authentication
    if (this.sessionToken) {
      headers['Authorization'] = `Bearer ${this.sessionToken}`;
      console.error(`[API] Using session token authentication`);
    } else {
      console.error(`[API] Warning: No authentication token available`);
    }

    console.error(`[API] ${options.method || 'GET'} ${url}`);
    if (options.body) {
      console.error(`[API] Body:`, options.body);
    }

    const response = await (fetch as any)(url, {
      ...options,
      headers: {
        ...headers,
        ...options.headers,
      },
    });

    console.error(`[API] Response: ${response.status} ${response.statusText}`);

    if (!response.ok) {
      const errorText = await response.text();
      console.error(`[API] Error response:`, errorText);
      throw new Error(
        `API request failed: ${response.status} ${response.statusText} - ${errorText}`,
      );
    }

    // Handle empty responses (like DELETE operations)
    if (
      response.status === 204 ||
      response.headers.get('content-length') === '0'
    ) {
      return null;
    }

    return await response.json();
  }

  async getTodos(): Promise<Todo[]> {
    const todos = await this.makeRequest('/todos');
    return todos || [];
  }

  async createTodo(text: string, completed = false): Promise<number> {
    const todo = { text, completed };
    return await this.makeRequest('/todos', {
      method: 'POST',
      body: JSON.stringify(todo),
    });
  }

  async updateTodo(
    id: number,
    updates: Partial<Omit<Todo, 'id'>>,
  ): Promise<Todo> {
    const updateData = { id, ...updates };
    return await this.makeRequest(`/todos/${id}`, {
      method: 'PATCH',
      body: JSON.stringify(updateData),
    });
  }

  async deleteTodo(id: number): Promise<void> {
    await this.makeRequest(`/todos/${id}`, {
      method: 'DELETE',
    });
  }

  async toggleTodo(id: number): Promise<Todo> {
    // First get the current todo to know its current state
    const todos = await this.getTodos();
    const todo = todos.find((t) => t.id === id);

    if (!todo) {
      throw new Error(`Todo with ID ${id} not found`);
    }

    return await this.updateTodo(id, { completed: !todo.completed });
  }

  async getTodoStats() {
    const todos = await this.getTodos();

    return {
      total: todos.length,
      completed: todos.filter((t) => t.completed).length,
      pending: todos.filter((t) => !t.completed).length,
      completionRate:
        todos.length > 0
          ? (
              (todos.filter((t) => t.completed).length / todos.length) *
              100
            ).toFixed(1) + '%'
          : '0%',
    };
  }
}
