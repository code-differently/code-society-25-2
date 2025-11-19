import htm from "htm";
import { createElement } from "react";
import { DemoSlide, Lesson } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental2() {
    return html`
        <${Lesson} title="How to Build A Website" lessonId="supplemental_2" subtitle="Supplemental 2">
            <section>
                <p>Building a website is more than just writing HTML and CSS.</p>
            </section>
            <section>
                <p>A <em>modern website</em> requires careful planning, design, and implementation across multiple layers.</p>
            </section>
            <section>
                <p>Every website needs <em>essential pages</em> to be functional and legally compliant.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Core pages every site needs</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Landing page</em> - First impression with clear value proposition</li>
                    <li class="fragment"><em>About page</em> - Who you are and what you do</li>
                    <li class="fragment"><em>Contact page</em> - How users can reach you</li>
                    <li class="fragment"><em>Legal pages</em> - Terms of service and privacy policy</li>
                </ul>
            </section>
            <section>
                <p>But a great website also needs a <em>solid backend</em> to manage content and users.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Essential backend systems</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Content management</em> - Create, edit, and publish content</li>
                    <li class="fragment"><em>User authentication</em> - Secure login and user accounts</li>
                    <li class="fragment"><em>Media management</em> - Store and serve images, videos, files</li>
                    <li class="fragment"><em>Email systems</em> - Transactional emails and notifications</li>
                </ul>
            </section>
            <section>
                <p>For e-commerce sites, you'll need <em>payment processing</em> and <em>inventory management</em>.</p>
            </section>
            <section>
                <p>Security is <em>not optional</em>—it must be built in from the start.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Critical security concerns</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>HTTPS/SSL</em> - Encrypt all data in transit</li>
                    <li class="fragment"><em>Authentication</em> - Strong passwords and multi-factor auth</li>
                    <li class="fragment"><em>Input validation</em> - Prevent SQL injection and XSS attacks</li>
                    <li class="fragment"><em>CSRF protection</em> - Protect against cross-site request forgery</li>
                </ul>
            </section>
            <section>
                <p>You also need to <em>monitor</em> your website's performance and user behavior.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Analytics and monitoring</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>User analytics</em> - Track visitor behavior and conversions</li>
                    <li class="fragment"><em>Performance monitoring</em> - Page load times and errors</li>
                    <li class="fragment"><em>Error tracking</em> - Catch bugs before users report them</li>
                </ul>
            </section>
            <section>
                <p>Once built, your site needs a <em>home on the internet</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Hosting and deployment</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Domain name</em> - Register your site's address (e.g., example.com)</li>
                    <li class="fragment"><em>Web hosting</em> - Where your files live (Vercel, Netlify, AWS)</li>
                    <li class="fragment"><em>SSL certificate</em> - Enable HTTPS for secure connections</li>
                    <li class="fragment"><em>Deployment pipeline</em> - Automate updates from code to production</li>
                </ul>
            </section>
            <section>
                <p>Static sites are <em>simpler</em> and can be hosted for <em>free</em>.</p>
            </section>
            <section>
                <p>Examples: <em>GitHub Pages</em>, <em>Netlify</em>, <em>Vercel</em>.</p>
            </section>
            <section>
                <p>Dynamic sites with databases need <em>more infrastructure</em> and ongoing maintenance.</p>
            </section>
            <section>
                <p>Examples: <em>AWS</em>, <em>Google Cloud</em>, <em>DigitalOcean</em>.</p>
            </section>
            <section>
                <p>Professional websites run in <em>multiple environments</em> before reaching users.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Development environments</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Development</em> - Your local machine where you write code</li>
                    <li class="fragment"><em>Staging</em> - Production-like environment for testing</li>
                    <li class="fragment"><em>Production</em> - Live site that users interact with</li>
                </ul>
            </section>
            <section>
                <p>Each environment has <em>different configuration</em>—database URLs, API keys, feature flags.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Environment configuration</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Environment variables</em> - Store secrets and config outside code</li>
                    <li class="fragment"><em>.env files</em> - Local config never committed to Git</li>
                    <li class="fragment"><em>Config management</em> - Use services like Vault or AWS Secrets Manager</li>
                    <li class="fragment"><em>Feature flags</em> - Enable/disable features per environment</li>
                </ul>
            </section>
            <section>
                <p><em>Never</em> hardcode secrets like API keys or passwords in your code.</p>
            </section>
            <section>
                <p>Use <em>environment-specific config</em> to point to the right databases and services.</p>
            </section>
            <section>
                <p>Before launching, <em>test everything</em>—broken websites lose users and revenue.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Testing strategies</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Unit tests</em> - Test individual functions and components</li>
                    <li class="fragment"><em>Integration tests</em> - Test how components work together</li>
                    <li class="fragment"><em>End-to-end tests</em> - Test complete user workflows</li>
                    <li class="fragment"><em>Manual testing</em> - Human QA to catch edge cases</li>
                </ul>
            </section>
            <section>
                <p>Use tools like <em>Jest</em>, <em>Playwright</em>, or <em>Cypress</em> to automate testing.</p>
            </section>
            <section>
                <p><em>Performance testing</em> ensures your site handles real-world traffic.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Performance testing</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Load testing</em> - Simulate many concurrent users</li>
                    <li class="fragment"><em>Stress testing</em> - Find breaking points</li>
                    <li class="fragment"><em>Lighthouse audits</em> - Check speed and best practices</li>
                </ul>
            </section>
            <section>
                <p>Don't forget <em>security testing</em>—scan for vulnerabilities regularly.</p>
            </section>
            <section>
                <p>Tools like <em>OWASP ZAP</em> and <em>Snyk</em> help find security issues automatically.</p>
            </section>
            <section>
                <p>Don't forget about <em>accessibility</em>—your site should work for everyone.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Accessibility best practices</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Semantic HTML</em> - Use proper heading and landmark tags</li>
                    <li class="fragment"><em>Alt text</em> - Describe images for screen readers</li>
                    <li class="fragment"><em>Keyboard navigation</em> - Site works without a mouse</li>
                    <li class="fragment"><em>Color contrast</em> - Text is readable for all users</li>
                </ul>
            </section>
            <section>
                <p>Use <em>automated accessibility testing</em> tools like Axe or Lighthouse.</p>
            </section>
            <section>
                <p>Plan for <em>responsive design</em>—your site must work on phones, tablets, and desktops.</p>
            </section>
            <section>
                <p>Use <em>CSS media queries</em> and <em>flexible layouts</em> to adapt to different screen sizes.</p>
            </section>
            <section>
                <p>Before launching, create a <em>site map</em> and <em>wireframes</em> to plan your structure.</p>
            </section>
            <section>
                <p>Tools like <em>Figma</em>, <em>Whimsical</em>, or even pen and paper help visualize your site.</p>
            </section>
            <section>
                <p>After launch, <em>maintenance is ongoing</em>—updates, backups, and monitoring never stop.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Ongoing website maintenance</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Regular backups</em> - Protect against data loss</li>
                    <li class="fragment"><em>Software updates</em> - Keep dependencies secure and current</li>
                    <li class="fragment"><em>Performance optimization</em> - Keep load times fast</li>
                    <li class="fragment"><em>SEO improvements</em> - Help users find your site</li>
                </ul>
            </section>
            <section>
                <p>Building a website is a <em>journey</em>, not a destination—start simple and iterate.</p>
            </section>
            <${DemoSlide} />
        <//>`;
}

export default Supplemental2;
