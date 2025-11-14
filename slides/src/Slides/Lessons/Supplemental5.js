import htm from "htm";
import { createElement } from "react";
import { CodeSlide, DemoSlide, Lesson, QuestionsSlide } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental5() {
    return html`
        <${Lesson} title="Agentic AI" lessonId="supplemental_5" subtitle="Supplemental 5">
            <section>
                <p>AI is evolving from <em>answering questions</em> to <em>taking actions</em>.</p>
            </section>
            <section>
                <p><em>Agentic AI</em> refers to AI systems that can <em>autonomously</em> make decisions and perform tasks.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>What makes an AI "agentic"?</h3>
                <ul>
                    <li class="fragment">Can <em>plan</em> multi-step tasks</li>
                    <li class="fragment">Can <em>use tools</em> to interact with systems</li>
                    <li class="fragment">Can <em>adapt</em> based on feedback</li>
                    <li class="fragment">Can <em>execute</em> without constant human guidance</li>
                </ul>
            </section>
            <section>
                <p>Instead of just chatting, agentic AI can <em>read files</em>, <em>run code</em>, <em>query databases</em>, and more.</p>
            </section>
            <section>
                <p>Think of it like a <em>junior developer</em> that can follow instructions and use your dev tools.</p>
            </section>
            <section>
                <p>But how does AI <em>safely</em> interact with all these different systems?</p>
            </section>
            <section>
                <p>That's where <em>MCP</em> comes in.</p>
            </section>
            <section>
                <p><em>Model Context Protocol (MCP)</em> is an open standard for connecting AI models to external tools and data.</p>
            </section>
            <section>
                <p>MCP lets AI <em>discover</em> and <em>use</em> capabilities without hardcoding every integration.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>MCP basic architecture</h3>
                <ul>
                    <li class="fragment"><em>Host</em> - The AI application (like Claude Desktop, VS Code)</li>
                    <li class="fragment"><em>Client</em> - Connects to MCP servers on behalf of the AI</li>
                    <li class="fragment"><em>Server</em> - Exposes tools, resources, and prompts</li>
                </ul>
            </section>
            <section>
                <p>An <em>MCP server</em> is just a program that follows the protocol and provides capabilities.</p>
            </section>
            <section>
                <p>MCP supports <em>three transport mechanisms</em> for client-server communication.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>MCP Transport Types</h3>
                <ul>
                    <li class="fragment"><em>stdio</em> - Standard input/output (for local processes)</li>
                    <li class="fragment"><em>SSE</em> - Server-Sent Events (for remote HTTP servers)</li>
                    <li class="fragment"><em>HTTP with Streaming</em> - Streamable HTTP (experimental)</li>
                </ul>
            </section>
            <section>
                <p><em>stdio</em> is the simplest transport—perfect for <em>local tools</em> on your machine.</p>
            </section>
            <${CodeSlide} lang="typescript" badge="stdio Transport" fontSize=".37em" lineNumbers=true>
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";

// Server reads from stdin, writes to stdout
const transport = new StdioServerTransport();
await server.connect(transport);

// Client launches the server process
const client = new Client({
  name: "my-client",
  version: "1.0.0"
});

await client.connect(new StdioClientTransport({
  command: "node",
  args: ["server.js"]
}));
            <//>
            <section>
                <p><em>SSE</em> (Server-Sent Events) enables <em>remote MCP servers</em> accessible over HTTP.</p>
            </section>
            <${CodeSlide} lang="typescript" badge="SSE Transport" fontSize=".34em" lineNumbers=true>
import { SSEServerTransport } from "@modelcontextprotocol/sdk/server/sse.js";

// Server sends events over HTTP
const server = new Server({ name: "remote-tools", version: "1.0.0" }, 
  { capabilities: { tools: {} } });

const transport = new SSEServerTransport("/messages", response);
await server.connect(transport);

// Client connects to remote URL
import { SSEClientTransport } from "@modelcontextprotocol/sdk/client/sse.js";

const client = new Client({ name: "my-client", version: "1.0.0" });
await client.connect(new SSEClientTransport(
  new URL("https://example.com/sse")
));
            <//>
            <section>
                <p><em>HTTP with Streaming</em> is an experimental transport for <em>bidirectional</em> communication.</p>
            </section>
            <${CodeSlide} lang="typescript" badge="Streamable HTTP" fontSize=".37em" lineNumbers=true>
// Experimental - subject to change
import { StreamableHTTPServerTransport } from 
  "@modelcontextprotocol/sdk/server/streamable-http.js";

const transport = new StreamableHTTPServerTransport({
  sessionId: request.headers.get("x-session-id")
});

await server.connect(transport);

// Supports full duplex streaming over HTTP
            <//>
            <section class="ml-bullet-slide">
                <h3>Choosing the right transport</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>stdio</em> - Local development tools, CLI utilities</li>
                    <li class="fragment"><em>SSE</em> - Cloud services, shared team tools, web integrations</li>
                    <li class="fragment"><em>Streamable HTTP</em> - Advanced use cases (still experimental)</li>
                </ul>
            </section>
            <${CodeSlide} lang="typescript" badge="MCP Server" fontSize=".30em" lineNumbers=true>
import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";

const server = new Server({
  name: "my-tool-server",
  version: "1.0.0"
}, {
  capabilities: { tools: {} }
});

// Define a tool the AI can use
server.setRequestHandler("tools/list", async () => ({
  tools: [{
    name: "get_weather",
    description: "Get current weather for a location",
    inputSchema: {
      type: "object",
      properties: {
        location: { type: "string" }
      }
    }
  }]
}));
            <//>
            <${CodeSlide} lang="typescript" badge="MCP Server" fontSize=".37em" lineNumbers=true>
// Handle tool execution
server.setRequestHandler("tools/call", async (request) => {
  if (request.params.name === "get_weather") {
    const { location } = request.params.arguments;
    // Fetch weather data...
    return {
      content: [{
        type: "text",
        text: \`Weather in \${location}: Sunny, 72°F\`
      }]
    };
  }
});

const transport = new StdioServerTransport();
await server.connect(transport);
            <//>
            <section>
                <p>The AI can now <em>discover</em> the weather tool and <em>call it</em> when needed.</p>
            </section>
            <section>
                <p>So how is MCP <em>different</em> from a regular API?</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>MCP vs Traditional APIs</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Discoverability</em> - MCP tools describe themselves; APIs need documentation</li>
                    <li class="fragment"><em>Context</em> - MCP provides resources and prompts alongside tools</li>
                    <li class="fragment"><em>Standardized</em> - One protocol for all integrations; APIs are unique</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>MCP vs Traditional APIs</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Stateful</em> - MCP maintains session context; REST is stateless</li>
                    <li class="fragment"><em>AI-first</em> - Designed for autonomous agents, not human developers</li>
                </ul>
            </section>
            <section>
                <p>APIs are built for <em>developers</em> to integrate systems manually.</p>
            </section>
            <section>
                <p>MCP is built for <em>AI agents</em> to integrate systems autonomously.</p>
            </section>
            <${CodeSlide} lang="json" badge="MCP Tool Response" fontSize=".42em">
{
  "content": [
    {
      "type": "text",
      "text": "Found 3 matching files"
    },
    {
      "type": "resource",
      "resource": {
        "uri": "file:///project/src/app.ts",
        "mimeType": "text/plain",
        "text": "// Source code here..."
      }
    }
  ]
}
            <//>
            <section>
                <p>MCP servers can be written in <em>any language</em> and run <em>locally</em> or <em>remotely</em>.</p>
            </section>
            <section>
                <p>Examples: <em>filesystem access</em>, <em>database queries</em>, <em>Git operations</em>, <em>web browsing</em>, <em>API calls</em>.</p>
            </section>
            <section>
                <p>With MCP, you can give AI <em>safe, controlled access</em> to your entire dev environment.</p>
            </section>
            <${DemoSlide} />
            <${QuestionsSlide} />
        <//>`;
}

export default Supplemental5;
