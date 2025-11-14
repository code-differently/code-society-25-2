# Supplemental 5: Agentic AI ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/supplemental_5))

## Pre-work

Please review the following resources before lecture:

### Recommended
* [What is Agentic AI and How Does it Work? (Video)](https://www.youtube.com/watch?v=15_pppse4fY)
* [Build an MCP server in your application with Clerk (Article)](https://clerk.com/docs/nextjs/guides/development/mcp/build-mcp-server)

## Demo: MCP Server Implementation

The `fullstack_demo` project includes a working Model Context Protocol (MCP) server that demonstrates how AI agents can interact with a real application.

### What's Included

The demo shows how to:
- Expose application functionality (todos) through MCP tools
- Implement authentication for AI agents using Clerk
- Support multiple transport types (stdio, SSE, HTTP)
- Deploy an MCP server to production (Vercel)

### Code Location

📂 **MCP Server Route**: [`lib/javascript/fullstack_demo/src/app/[transport]/route.ts`](../lib/javascript/fullstack_demo/src/app/[transport]/route.ts)

This file demonstrates:
- Creating MCP tools with `createMcpHandler`
- Defining tool schemas with Zod validation
- Implementing authentication with `withMcpAuth`
- Handling different transport mechanisms

### Quick Start

```bash
cd lib/javascript/fullstack_demo
npm install
npm run dev

# In another terminal, connect an AI agent
npx -y mcp-remote http://localhost:3000/mcp
```

### Full Documentation

For detailed setup instructions and architecture details, see:
- [MCP Quick Start Guide](../lib/javascript/fullstack_demo/docs/MCP_QUICKSTART.md)
- [MCP Server Setup](../lib/javascript/fullstack_demo/docs/MCP_SERVER_SETUP.md)
- [Main README](../lib/javascript/fullstack_demo/README.md#mcp-server---ai-agent-integration)
