import htm from "htm";
import { createElement } from "react";
import { CodeSlide, DemoSlide, Lesson, QuestionsSlide } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental3() {
    return html`
        <${Lesson} title="Containerization with Docker" lessonId="supplemental_3" subtitle="Supplemental 3">
            <section>
                <p>Running software <em>consistently</em> across <em>different machines</em> is hard.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common problems containers solve</h3>
                <ul>
                    <li class="fragment">“Works on my machine” environment drift</li>
                    <li class="fragment">Dependency and version conflicts</li>
                    <li class="fragment">Manual, error-prone setup steps</li>
                    <li class="fragment">Scaling and isolation of services</li>
                </ul>
            </section>
            <section>
                <p><em>Containerization</em> packages your <em>app and its dependencies</em> into a portable, isolated unit.</p>
            </section>
            <section>
                <p>Containers <em>share the host OS kernel</em> and start fast (unlike heavy virtual machines).</p>
            </section>
            <section>
                <p><em>Docker</em> is the most popular tool for building, running, and sharing containers.</p>
            </section>
            <section>
                <p><em>Kubernetes</em> orchestrates containers across many machines for <em>reliability</em> and <em>scale</em>.</p>
            </section>
            <section>
                <p><em>Netflix</em>, <em>Airbnb</em>, <em>Shopify</em>, <em>Google</em>, <em>Reddit</em> (and many more) use containers in production.</p>
            </section>
            <section>
                <p>Getting started is simple: <em>install Docker Desktop</em> and <em>run a container</em>.</p>
            </section>
            <${CodeSlide} lang="bash" badge="Terminal" fontSize=".42em" lineNumbers=true>
# Verify installation
docker --version

# Pull and run a test image
docker run --rm hello-world
            <//>
            <section>
                <p>Let’s containerize a simple <em>Node.js</em> web app.</p>
            </section>
            <${CodeSlide} lang="javascript" badge="app.js" fontSize=".42em" lineNumbers=true>
// Minimal HTTP server (no external deps)
import http from 'http';

const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ ok: true, time: new Date().toISOString() }));
});

server.listen(3000, () => console.log('Listening on http://localhost:3000'));
            <//>
            <${CodeSlide} lang="dockerfile" badge="Dockerfile" fontSize=".38em" lineNumbers=true>
# Use a small official Node image
FROM node:20-alpine

# Create app directory
WORKDIR /usr/src/app

# Copy source
COPY app.js ./

# Expose port and run
EXPOSE 3000
CMD ["node", "app.js"]
            <//>
            <${CodeSlide} lang="bash" badge="Terminal" fontSize=".42em" lineNumbers=true>
# Build image (tag as demo:latest)
docker build -t demo:latest .

# Run container and map port 3000
docker run --rm -p 3000:3000 demo:latest
            <//>
            <section>
                <p>Use <em>Docker Compose</em> to define <em>multi-service environments</em> in <em>one file</em>.</p>
            </section>
            <${CodeSlide} lang="yaml" badge="docker-compose.yml" fontSize=".34em">
services:
  web:
    build: .
    ports:
      - "3000:3000"
    depends_on:
      - db
  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_USER: app
      POSTGRES_PASSWORD: secret
      POSTGRES_DB: appdb
    volumes:
      - db_data:/var/lib/postgresql/data
volumes:
  db_data:
            <//>
            <${CodeSlide} lang="bash" badge="Compose" fontSize=".42em" lineNumbers=true>
# Start all services
docker compose up --build

# Stop and remove containers
docker compose down
            <//>
            <section>
                <p>With Docker Compose, teams share reproducible dev and test environments as code.</p>
            </section>
            <${DemoSlide} />
            <${QuestionsSlide} />
        <//>`;
}

export default Supplemental3;
