import htm from "htm";
import { createElement } from "react";
import { Lesson } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental8() {
    return html`
        <${Lesson} title="Cloud Computing" lessonId="supplemental_8" subtitle="Supplemental 8" titleFontSize="1.2em">
            <section class="ml-bullet-slide">
                <h3>Good to review:</h3>
                <ul style=${{"font-size": ".95em"}}>
                    <li class="fragment"><i>Lesson 10</i> - Libraries and APIs</li>
                    <li class="fragment"><i>Lesson 19</i> - Building Applications</li>
                    <li class="fragment"><i>Supplemental 1</i> - Content Management Systems</li>
                    <li class="fragment"><i>Supplemental 3</i> - Containerization</li>
                    <li class="fragment"><i>Supplemental 6</i> - Microservices</li>
                </ul>
            </section>
            <section>
                <p>In the past, companies had to buy and maintain their own <em>servers</em> and <em>data centers</em></p>
            </section>
            <section>
                <p>This required <i>expensive hardware</i>, <i>physical space</i>, <i>cooling systems</i>, and <i>IT staff</i> to manage everything</p>
            </section>
            <section>
                <p>What if you could <em>rent</em> computing power instead of <em>buying</em> it?</p>
            </section>
            <section>
                <p><em><i>Cloud computing</i></em> is the delivery of computing services over the internet</p>
            </section>
            <section>
                <p>Instead of owning physical servers, you <em>rent</em> computing resources from a cloud provider</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Why do it?</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">No upfront hardware costs</li>
                    <li class="fragment">Scale resources up or down on demand</li>
                    <li class="fragment">Deploy globally in minutes</li>
                    <li class="fragment">Disaster recovery and backups</li>
                    <li class="fragment">Focus on building apps, not managing infrastructure</li>
                </ul>
            </section>
            <section>
                <p>Let's look at some examples of companies using cloud infrastructure</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Real-world cloud success stories</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Netflix</em> - Streams to 200M+ subscribers using AWS</li>
                    <li class="fragment"><em>Spotify</em> - Delivers music to 500M+ users with Google Cloud</li>
                    <li class="fragment"><em>Airbnb</em> - Scaled from startup to global platform on AWS</li>
                    <li class="fragment"><em>Slack</em> - Supports millions of concurrent users on AWS</li>
                </ul>
            </section>
            <section>
                <p>Now let's talk about <em>cloud service models</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Three main service models</h3>
                <ul>
                    <li class="fragment"><em>IaaS</em> - Infrastructure as a Service</li>
                    <li class="fragment"><em>PaaS</em> - Platform as a Service</li>
                    <li class="fragment"><em>SaaS</em> - Software as a Service</li>
                </ul>
            </section>
            <section>
                <p><em><i>IaaS</i></em> gives you virtual machines, storage, and networking</p>
                <p class="fragment" style=${{"font-size": ".8em", "margin-top": "1em"}}>Examples: AWS EC2, Google Compute Engine, Azure VMs</p>
            </section>
            <section>
                <p><em><i>PaaS</i></em> provides a platform to deploy your applications without managing the infrastructure</p>
                <p class="fragment" style=${{"font-size": ".8em", "margin-top": "1em"}}>Examples: Heroku, Google App Engine, Azure App Service</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Modern PaaS platforms</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Vercel</em> - Optimized for frontend frameworks (Next.js, React)</li>
                    <li class="fragment"><em>Netlify</em> - JAMstack and static site deployments</li>
                    <li class="fragment"><em>Render</em> - Full-stack apps with databases</li>
                    <li class="fragment"><em>Fly.io</em> - Global edge deployment</li>
                </ul>
            </section>
            <section>
                <p><em><i>SaaS</i></em> delivers complete applications over the internet</p>
                <p class="fragment" style=${{"font-size": ".8em", "margin-top": "1em"}}>Examples: Gmail, Salesforce, Dropbox, Office 365</p>
            </section>
            <section>
                <p>Let's explore the <em>core components</em> of cloud infrastructure</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Compute Services</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Virtual Machines</em> - EC2, Compute Engine, Azure VMs</li>
                    <li class="fragment"><em>Containers</em> - ECS, Kubernetes, AKS</li>
                    <li class="fragment"><em>Serverless Functions</em> - Lambda, Cloud Functions, Azure Functions</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Storage Services</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Object Storage</em> - S3, Cloud Storage, Blob Storage</li>
                    <li class="fragment"><em>Block Storage</em> - EBS, Persistent Disk, Managed Disks</li>
                    <li class="fragment"><em>File Storage</em> - EFS, Filestore, Azure Files</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Networking</h3>
                <ul>
                    <li class="fragment">Virtual Private Clouds (VPC)</li>
                    <li class="fragment">Load Balancers</li>
                    <li class="fragment">Content Delivery Networks (CDN)</li>
                    <li class="fragment">DNS Services</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Database Services</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Relational</em> - RDS, Cloud SQL, Azure SQL</li>
                    <li class="fragment"><em>NoSQL</em> - DynamoDB, Firestore, Cosmos DB</li>
                    <li class="fragment"><em>Data Warehouses</em> - Redshift, BigQuery, Synapse</li>
                </ul>
            </section>
            <section>
                <p>Now let's talk about <em>security</em></p>
            </section>
            <section>
                <p><em><i>Identity and Access Management (IAM)</i></em> controls who can access your cloud resources</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Core IAM concepts</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Users</em> - Individual accounts with credentials</li>
                    <li class="fragment"><em>Groups</em> - Collections of users with shared permissions</li>
                    <li class="fragment"><em>Roles</em> - Sets of permissions that can be assumed</li>
                    <li class="fragment"><em>Policies</em> - Documented permissions</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>IAM Best Practices</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Principle of least privilege</em> - Grant minimal permissions needed</li>
                    <li class="fragment">Use multi-factor authentication (<em>MFA</em>)</li>
                    <li class="fragment">Rotate credentials regularly</li>
                    <li class="fragment">Use <em>roles</em> instead of long-term credentials</li>
                    <li class="fragment"><em>Audit</em> access logs regularly</li>
                </ul>
            </section>
            <section>
                <p>One of the biggest concerns with cloud computing is <em>cost</em></p>
            </section>
            <section>
                <p>Without proper management, cloud costs can <i>spiral out of control</i></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Cost optimization strategies</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Right-sizing</em> - Match instance sizes to actual needs</li>
                    <li class="fragment"><em>Reserved Instances</em> - Commit to 1-3 years for 30-70% savings</li>
                    <li class="fragment"><em>Spot/Preemptible Instances</em> - Use for fault-tolerant workloads</li>
                    <li class="fragment"><em>Auto-scaling</em> - Scale down during low-traffic periods</li>
                    <li class="fragment"><em>Delete unused resources</em> - Remove orphaned volumes, snapshots</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Cost monitoring tools</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Budget alerts and spending limits</li>
                    <li class="fragment">Cost allocation tags by team/project</li>
                    <li class="fragment">Usage dashboards and reports</li>
                    <li class="fragment">Third-party optimization tools</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Major cloud providers</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Amazon Web Services (AWS)</em> - Market leader, most services</li>
                    <li class="fragment"><em>Microsoft Azure</em> - Strong enterprise integration</li>
                    <li class="fragment"><em>Google Cloud Platform (GCP)</em> - AI/ML and data analytics</li>
                    <li class="fragment"><em>Oracle Cloud</em> - Database and enterprise workloads</li>
                </ul>
            </section>
            <section>
                <p>Let's see how to build the <em>same application</em> on different cloud providers</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Example: E-commerce web app</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Frontend</em> - React app served globally</li>
                    <li class="fragment"><em>Backend API</em> - REST API with authentication</li>
                    <li class="fragment"><em>Database</em> - PostgreSQL relational database</li>
                    <li class="fragment"><em>CDN</em> - Content delivery for fast loading</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Building on <em>Google Cloud</em></h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Frontend</em> → Cloud Storage + Firebase Hosting</li>
                    <li class="fragment"><em>Backend API</em> → Cloud Run (containerized)</li>
                    <li class="fragment"><em>Database</em> → Cloud SQL (PostgreSQL)</li>
                    <li class="fragment"><em>CDN</em> → Cloud CDN</li>
                    <li class="fragment"><em>Auth</em> → Identity Platform</li>
                    <li class="fragment"><em>Monitoring</em> → Cloud Logging + Cloud Monitoring</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Building on <em>AWS</em></h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Frontend</em> → S3 + CloudFront</li>
                    <li class="fragment"><em>Backend API</em> → ECS Fargate or Lambda + API Gateway</li>
                    <li class="fragment"><em>Database</em> → RDS PostgreSQL</li>
                    <li class="fragment"><em>CDN</em> → CloudFront</li>
                    <li class="fragment"><em>Auth</em> → Cognito</li>
                    <li class="fragment"><em>Monitoring</em> → CloudWatch</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Building on <em>Azure</em></h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Frontend</em> → Static Web Apps or Blob Storage</li>
                    <li class="fragment"><em>Backend API</em> → App Service or Container Apps</li>
                    <li class="fragment"><em>Database</em> → Azure Database for PostgreSQL</li>
                    <li class="fragment"><em>CDN</em> → Azure CDN or Front Door</li>
                    <li class="fragment"><em>Auth</em> → Azure AD B2C</li>
                    <li class="fragment"><em>Monitoring</em> → Application Insights + Azure Monitor</li>
                </ul>
            </section>
            <section>
                <p>The concepts you learn apply across <em>all cloud providers</em></p>
            </section>
            <section>
                <p>Cloud <em>certifications</em> can boost your career prospects</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Why get cloud certified?</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Validates your cloud knowledge to employers</li>
                    <li class="fragment">Often required or preferred for cloud roles</li>
                    <li class="fragment">Can increase salary potential by 10-20%</li>
                    <li class="fragment">Demonstrates commitment to professional growth</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Entry-level certifications</h3>
                <ul style=${{"font-size": ".75em"}}>
                    <li class="fragment"><em>AWS Certified Cloud Practitioner</em> - Great first cert, covers cloud fundamentals</li>
                    <li class="fragment"><em>Microsoft Azure Fundamentals (AZ-900)</em> - Entry-level Azure knowledge</li>
                    <li class="fragment"><em>Google Cloud Digital Leader</em> - Business and technical cloud concepts</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Key takeaways</h3>
                <ul style=${{"font-size": ".82em"}}>
                    <li class="fragment">Cloud computing provides on-demand computing resources</li>
                    <li class="fragment">Major companies rely on cloud to scale globally</li>
                    <li class="fragment">Cost optimization requires continuous monitoring</li>
                    <li class="fragment">Skills are transferable across providers</li>
                </ul>
            </section>
        <//>`;
}

export default Supplemental8;
