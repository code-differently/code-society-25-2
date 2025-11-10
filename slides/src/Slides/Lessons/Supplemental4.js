import htm from "htm";
import { createElement } from "react";
import { CodeSlide, DemoSlide, Lesson } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental4() {
    return html`
        <${Lesson} title="Integration Testing" lessonId="supplemental_4" subtitle="Supplemental 4">
            <section>
                <p><em>Unit tests</em> verify individual functions work in isolation.</p>
            </section>
            <section>
                <p><em>Integration tests</em> verify that multiple components work together correctly.</p>
            </section>
            <section>
                <p>Integration tests catch bugs that unit tests miss—like <em>API failures</em>, <em>database issues</em>, and <em>UI interactions</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Unit vs Integration Testing</h3>
                <ul>
                    <li class="fragment"><em>Unit</em>: Fast, isolated, mocks dependencies</li>
                    <li class="fragment"><em>Integration</em>: Slower, uses real services, tests workflows</li>
                    <li class="fragment">Both are essential for quality software</li>
                </ul>
            </section>
            <section>
                <p>Integration tests often require <em>databases</em>, <em>APIs</em>, and <em>browsers</em> to be running.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common Integration Testing Tools</h3>
                <ul>
                    <li class="fragment"><em>Browser automation</em>: Playwright, Selenium, Cypress</li>
                    <li class="fragment"><em>API testing</em>: Supertest, REST Assured</li>
                    <li class="fragment"><em>Screenshot/visual testing</em>: Percy, Chromatic</li>
                </ul>
            </section>
            <section>
                <p><em>Playwright</em> is a modern browser automation tool that makes integration testing fast and reliable.</p>
            </section>
            <section class="ml-bullet-slide" fontSize=".42em">
                <h3>Why Playwright?</h3>
                <ul>
                    <li class="fragment">Tests Chrome, Firefox, Safari, and mobile browsers</li>
                    <li class="fragment">Auto-waits for elements (no flaky tests)</li>
                    <li class="fragment">Built-in screenshot and video recording</li>
                </ul>
            </section>
            <section>
                <p>Let's set up Playwright for a web app.</p>
            </section>
            <${CodeSlide} lang="bash" badge="Terminal" fontSize=".42em" lineNumbers=true>
# Install Playwright
npm init playwright@latest

# Installs test runner, browsers, and example tests
            <//>
            <${CodeSlide} lang="typescript" badge="tests/example.spec.ts" fontSize=".38em" lineNumbers=true>
import { test, expect } from '@playwright/test';

test('homepage loads correctly', async ({ page }) => {
  // Navigate to homepage
  await page.goto('http://localhost:3000');
  
  // Check title
  await expect(page).toHaveTitle(/My App/);
  
  // Click button and verify result
  await page.click('button#submit');
  await expect(page.locator('.result')).toHaveText('Success!');
});
            <//>
            <${CodeSlide} lang="bash" badge="Terminal" fontSize=".42em" lineNumbers=true>
# Run all tests
npx playwright test

# Run tests in headed mode (see browser)
npx playwright test --headed

# Open test report
npx playwright show-report
            <//>
            <section>
                <p>Integration tests need services running—use <em>scripts</em> to automate setup.</p>
            </section>
            <${CodeSlide} lang="json" badge="package.json" fontSize=".36em" lineNumbers=true>
{
  "scripts": {
    "start": "node app.js",
    "test:unit": "jest",
    "test:integration": "playwright test",
    "test:integration:headed": "playwright test --headed",
    "pretest:integration": "npm run db:setup && npm run start:ci",
    "start:ci": "node app.js &",
    "db:setup": "node scripts/setup-db.js"
  }
}
            <//>
            <section>
                <p>Use <em>wait-on</em> to ensure services are ready before running tests.</p>
            </section>
            <${CodeSlide} lang="json" badge="package.json" fontSize=".38em" lineNumbers=true>
{
  "scripts": {
    "pretest:integration": "npm run start:ci",
    "start:ci": "node app.js & wait-on http://localhost:3000",
    "test:integration": "playwright test"
  },
  "devDependencies": {
    "wait-on": "^7.0.0"
  }
}
            <//>
            <section>
                <p><em>Docker</em> enables <em>hermetic testing</em>—fully isolated, reproducible test environments.</p>
            </section>
            <section>
                <p>Hermetic tests run in clean containers with <em>no external dependencies</em> or state.</p>
            </section>
            <${CodeSlide} lang="yaml" badge="docker-compose.test.yml" fontSize=".32em" lineNumbers=true>
services:
  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: testdb
      POSTGRES_USER: test
      POSTGRES_PASSWORD: test
  
  app:
    build: .
    environment:
      DATABASE_URL: postgres://test:test@db:5432/testdb
    depends_on:
      - db
    ports:
      - "3000:3000"
  
  tests:
    build:
      context: .
      dockerfile: Dockerfile.test
    depends_on:
      - app
    command: npx playwright test
            <//>
            <${CodeSlide} lang="bash" badge="Terminal" fontSize=".42em" lineNumbers=true>
# Run tests in isolated Docker environment
docker compose -f docker-compose.test.yml up --abort-on-container-exit

# Clean up after tests
docker compose -f docker-compose.test.yml down -v
            <//>
            <section>
                <p><em>GitHub Actions</em> can run your Docker-based tests on every push and pull request.</p>
            </section>
            <${CodeSlide} lang="yaml" badge=".github/workflows/test.yml" fontSize=".30em" lineNumbers=true>
name: Integration Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up Docker Buildx
        uses: docker/setup-buildx-action@v3
      
      - name: Run integration tests
        run: |
          docker compose -f docker-compose.test.yml up \\
            --abort-on-container-exit --exit-code-from tests
      
      - name: Upload test results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: playwright-report
          path: playwright-report/
            <//>
            <section>
                <p>With Docker and CI, every code change is automatically tested in a <em>clean</em>, <em>consistent</em> environment.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Integration Testing Best Practices</h3>
                <ul>
                    <li class="fragment">Keep tests fast—use parallelization</li>
                    <li class="fragment">Test critical user flows, not everything</li>
                    <li class="fragment">Use Docker for consistency across machines</li>
                    <li class="fragment">Run tests in CI on every commit</li>
                </ul>
            </section>
            <${DemoSlide} />
        <//>`;
}

export default Supplemental4;
