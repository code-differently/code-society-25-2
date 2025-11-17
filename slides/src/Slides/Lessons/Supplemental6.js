import htm from "htm";
import { createElement } from "react";
import { CodeSlide, DemoSlide, Lesson, QuestionsSlide } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental6() {
    return html`
        <${Lesson} title="Microservices" lessonId="supplemental_6" subtitle="Supplemental 6">
            <section>
                <p>Most applications start as <em>monoliths</em>—everything in one codebase.</p>
            </section>
            <section>
                <p>A <em>monolithic application</em> is a single, unified software system where <em>all components are tightly coupled</em>.</p>
            </section>
            <section>
                <p>In a monolith, the <em>UI</em>, <em>business logic</em>, and <em>data layer</em> are all bundled together.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Monolithic architecture example</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Single codebase with all features</li>
                    <li class="fragment">One database shared by all modules</li>
                    <li class="fragment">Deploy the entire app as one unit</li>
                    <li class="fragment">Scale by replicating the whole application</li>
                </ul>
            </section>
            <section>
                <p>Monoliths are <em>simple to start</em> with, but they <em>don't scale well</em> as apps grow.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Disadvantages of monoliths</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Tight coupling</em> - Changes in one area can break unrelated features</li>
                    <li class="fragment"><em>Slow deployment</em> - Must redeploy entire app for small changes</li>
                    <li class="fragment"><em>Difficult scaling</em> - Can't scale individual features independently</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Disadvantages of monoliths</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Technology lock-in</em> - Hard to adopt new languages or frameworks</li>
                    <li class="fragment"><em>Large codebase</em> - Difficult for new developers to understand</li>
                    <li class="fragment"><em>Single point of failure</em> - One bug can crash the entire system</li>
                </ul>
            </section>
            <section>
                <p>As companies like <em>Amazon</em>, <em>Netflix</em>, and <em>Uber</em> grew, monoliths became <em>unmaintainable</em>.</p>
            </section>
            <section>
                <p>That's why they moved to <em>microservices architecture</em>.</p>
            </section>
            <section>
                <p><em>Microservices</em> break a large application into <em>small, independent services</em>.</p>
            </section>
            <section>
                <p>Each microservice is a <em>self-contained unit</em> that handles a specific <em>business capability</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Microservices architecture example</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">User service manages authentication</li>
                    <li class="fragment">Order service handles purchases</li>
                    <li class="fragment">Payment service processes transactions</li>
                    <li class="fragment">Notification service sends emails/SMS</li>
                </ul>
            </section>
            <section>
                <p>Each service can be <em>developed</em>, <em>deployed</em>, and <em>scaled independently</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Problems microservices solve</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Independent deployment</em> - Update one service without touching others</li>
                    <li class="fragment"><em>Technology flexibility</em> - Use different languages/frameworks per service</li>
                    <li class="fragment"><em>Team autonomy</em> - Teams own specific services end-to-end</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Problems microservices solve</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Targeted scaling</em> - Scale only the services that need it</li>
                    <li class="fragment"><em>Fault isolation</em> - One service failure doesn't crash everything</li>
                    <li class="fragment"><em>Faster development</em> - Smaller codebases are easier to understand</li>
                </ul>
            </section>
            <section>
                <p>But microservices aren't <em>magic</em>—they introduce <em>complexity</em>.</p>
            </section>
            <section>
                <p>To build effective microservices, you need to follow <em>key architectural principles</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Rules of microservices architecture</h3>
                <ul style=${{"font-size": ".82em"}}>
                    <li class="fragment"><em>Single Responsibility</em> - Each service does one thing well</li>
                    <li class="fragment"><em>Decentralized Data</em> - Each service owns its own database</li>
                    <li class="fragment"><em>API-first Communication</em> - Services talk via well-defined APIs</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Rules of microservices architecture</h3>
                <ul style=${{"font-size": ".82em"}}>
                    <li class="fragment"><em>Independent Deployment</em> - Deploy without coordinating with other teams</li>
                    <li class="fragment"><em>Design for Failure</em> - Assume services will fail and plan accordingly</li>
                    <li class="fragment"><em>Observable Services</em> - Built-in logging, metrics, and tracing</li>
                </ul>
            </section>
            <section>
                <p>Let's look at how services <em>communicate</em> in a microservices architecture.</p>
            </section>
            <${CodeSlide} lang="typescript" badge="User Service API" fontSize=".35em" lineNumbers=true>
// User service exposes REST API
import express from 'express';

const app = express();

app.get('/users/:id', async (req, res) => {
  const user = await db.users.findById(req.params.id);
  res.json(user);
});

app.post('/users', async (req, res) => {
  const newUser = await db.users.create(req.body);
  res.status(201).json(newUser);
});

app.listen(3001, () => console.log('User service on :3001'));
            <//>
            <${CodeSlide} lang="typescript" badge="Order Service" fontSize=".35em" lineNumbers=true>
// Order service calls User service
import axios from 'axios';

async function createOrder(userId, items) {
  // Fetch user info from User service
  const userResponse = await axios.get(
    \`http://user-service:3001/users/\${userId}\`
  );
  
  const user = userResponse.data;
  
  // Create order with user details
  const order = await db.orders.create({
    userId: user.id,
    userEmail: user.email,
    items
  });
  
  return order;
}
            <//>
            <section>
                <p>Services can communicate <em>synchronously</em> (REST, gRPC) or <em>asynchronously</em> (message queues).</p>
            </section>
            <${CodeSlide} lang="typescript" badge="Event-Driven Communication" fontSize=".33em" lineNumbers=true>
// Order service publishes event
import { EventEmitter } from 'events';

const eventBus = new EventEmitter();

async function createOrder(userId, items) {
  const order = await db.orders.create({ userId, items });
  
  // Publish event instead of direct call
  eventBus.emit('order.created', {
    orderId: order.id,
    userId,
    total: order.total
  });
  
  return order;
}

// Notification service listens for event
eventBus.on('order.created', async (event) => {
  await sendEmail(event.userId, \`Order \${event.orderId} confirmed!\`);
});
            <//>
            <section>
                <p>Now let's talk about the <em>tools and technologies</em> used to build microservices.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Communication protocols</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>REST</em> - Simple HTTP APIs with JSON (most common)</li>
                    <li class="fragment"><em>gRPC</em> - High-performance binary protocol (faster than REST)</li>
                    <li class="fragment"><em>GraphQL</em> - Flexible query language for APIs</li>
                    <li class="fragment"><em>Message brokers</em> - RabbitMQ, Kafka for async communication</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Service discovery and routing</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>API Gateway</em> - Single entry point for all client requests (Kong, AWS API Gateway)</li>
                    <li class="fragment"><em>Service mesh</em> - Manages service-to-service communication (Istio, Linkerd)</li>
                    <li class="fragment"><em>Load balancers</em> - Distribute traffic across service instances (NGINX, HAProxy)</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Containerization and orchestration</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Docker</em> - Package services into containers</li>
                    <li class="fragment"><em>Kubernetes</em> - Orchestrate and scale containers</li>
                    <li class="fragment"><em>Docker Compose</em> - Define multi-service environments locally</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Observability and monitoring</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Logging</em> - Centralized logging with ELK stack or Splunk</li>
                    <li class="fragment"><em>Metrics</em> - Prometheus, Grafana for monitoring</li>
                    <li class="fragment"><em>Tracing</em> - Distributed tracing with Jaeger or Zipkin</li>
                </ul>
            </section>
            <section>
                <p>Microservices aren't always the <em>right choice</em>—they add operational complexity.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>When to use microservices</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Large teams working on different features</li>
                    <li class="fragment">Need to scale specific parts independently</li>
                    <li class="fragment">Different services have different tech requirements</li>
                    <li class="fragment">Frequent deployments with minimal downtime</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>When to stick with a monolith</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Small team or early-stage startup</li>
                    <li class="fragment">Simple application with few features</li>
                    <li class="fragment">Limited DevOps resources or expertise</li>
                    <li class="fragment">Unclear domain boundaries</li>
                </ul>
            </section>
            <section>
                <p><em>Start with a monolith</em>, then break it apart when complexity demands it.</p>
            </section>
            <section>
                <p>Companies like <em>Amazon</em> and <em>Netflix</em> evolved to microservices over <em>years</em>, not overnight.</p>
            </section>
            <section>
                <p>With microservices, you trade <em>simplicity</em> for <em>scalability and flexibility</em>.</p>
            </section>
            <${DemoSlide} />
            <${QuestionsSlide} />
        <//>`;
}

export default Supplemental6;
