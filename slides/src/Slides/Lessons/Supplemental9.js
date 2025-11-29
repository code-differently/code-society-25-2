import htm from "htm";
import { createElement } from "react";
import { Lesson } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental9() {
    return html`
        <${Lesson} title="Design Interviews" lessonId="supplemental_9" subtitle="Supplemental 9" titleFontSize="1.2em">
            <section class="ml-bullet-slide">
                <h3>Good to review:</h3>
                <ul style=${{"font-size": ".95em"}}>
                    <li class="fragment"><i>Lesson 14</i> - Exception Handling</li>
                    <li class="fragment"><i>Lesson 15</i> - Test-Driven Development</li>
                    <li class="fragment"><i>Lesson 16</i> - Object-Oriented Programming</li>
                    <li class="fragment"><i>Supplemental 6</i> - Microservices</li>
                    <li class="fragment"><i>Supplemental 8</i> - Cloud Computing</li>
                </ul>
            </section>
            <section>
                <p>Design interviews are a <em>critical part</em> of the software engineering interview process</p>
            </section>
            <section>
                <p>They test your ability to <em>solve complex problems</em> and <em>architect solutions</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>What do design interviews assess?</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Your <em>technical knowledge</em> and depth of understanding</li>
                    <li class="fragment">How you <em>break down</em> complex problems</li>
                    <li class="fragment">Your ability to <em>communicate</em></li>
                    <li class="fragment">How you make <em>tradeoffs</em> and justify decisions</li>
                    <li class="fragment">Your understanding of <em>scalability</em> and <em>reliability</em></li>
                </ul>
            </section>
            <section>
                <p>Why do <em>junior engineers</em> need to know design in the <em>AI age</em>?</p>
            </section>
            <section>
                <p>AI tools like <em>ChatGPT</em> and <em>Copilot</em> can write code...</p>
            </section>
            <section>
                <p>But they <em>can't</em> make high-level architectural decisions</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>The AI reality</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">You still need to <em>design</em> the solution</li>
                    <li class="fragment">Understanding <em>tradeoffs</em> requires human judgment</li>
                    <li class="fragment">AI doesn't know your <em>specific constraints</em></li>
                    <li class="fragment">Design skills make you <em>irreplaceable</em></li>
                </ul>
            </section>
            <section>
                <p>There are <em>two main types</em> of design interviews</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Two types of design interviews</h3>
                <ul>
                    <li class="fragment"><em>Coding Design</em> - Object-oriented design and code structure</li>
                    <li class="fragment"><em>System Design</em> - Distributed systems and infrastructure</li>
                </ul>
            </section>
            <section>
                <p>Let's start with <em>Coding Design</em> interviews</p>
            </section>
            <section>
                <p><em><i>Coding design</i></em> focuses on how you structure <i>classes</i>, <i>methods</i>, and <i>data</i></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common coding design questions</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Design a <em>parking lot</em> system</li>
                    <li class="fragment">Design a <em>library management</em> system</li>
                    <li class="fragment">Design a <em>deck of cards</em></li>
                    <li class="fragment">Design an <em>elevator</em> control system</li>
                    <li class="fragment">Design a <em>coffee maker</em> interface</li>
                </ul>
            </section>
            <section>
                <p>To excel at coding design, you need to master <em>SOLID principles</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Know your language's features</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Classes and Interfaces</em> - When to use each</li>
                    <li class="fragment"><em>Inheritance vs Composition</em> - Prefer composition</li>
                    <li class="fragment"><em>Enums</em> - Type-safe constants</li>
                    <li class="fragment"><em>Generics</em> - Type-safe collections</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Master data types and collections</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Lists</em> - Ordered collections (ArrayList, LinkedList)</li>
                    <li class="fragment"><em>Sets</em> - Unique elements (HashSet, TreeSet)</li>
                    <li class="fragment"><em>Maps</em> - Key-value pairs (HashMap, TreeMap)</li>
                    <li class="fragment"><em>Queues</em> - FIFO processing (PriorityQueue, LinkedList)</li>
                    <li class="fragment"><em>Stacks</em> - LIFO processing</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>When to use which collection?</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Need <em>order</em> and <em>duplicates</em>? → List</li>
                    <li class="fragment">Need <em>uniqueness</em>? → Set</li>
                    <li class="fragment">Need <em>key-value lookup</em>? → Map</li>
                    <li class="fragment">Need <em>priority processing</em>? → PriorityQueue</li>
                    <li class="fragment">Need <em>sorted order</em>? → TreeSet or TreeMap</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Design patterns to know</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Singleton</em> - One instance (database connection)</li>
                    <li class="fragment"><em>Factory</em> - Object creation logic</li>
                    <li class="fragment"><em>Builder</em> - Complex object construction</li>
                    <li class="fragment"><em>Observer</em> - Event notifications</li>
                    <li class="fragment"><em>Strategy</em> - Interchangeable algorithms</li>
                </ul>
            </section>
            <section>
                <p>Now let's talk about <em>System Design</em> interviews</p>
            </section>
            <section>
                <p><em><i>System design</i></em> focuses on <i>distributed systems</i> and <i>scalable architectures</i></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common system design questions</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Design <em>Twitter</em> or a social media feed</li>
                    <li class="fragment">Design <em>YouTube</em> or a video streaming service</li>
                    <li class="fragment">Design <em>Uber</em> or a ride-sharing app</li>
                    <li class="fragment">Design a <em>URL shortener</em> (like bit.ly)</li>
                    <li class="fragment">Design a <em>chat application</em> (like WhatsApp)</li>
                </ul>
            </section>
            <section>
                <p>The first step is to <em>do your homework</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Research popular systems</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Read <em>white papers</em> from tech companies</li>
                    <li class="fragment">Watch <em>YouTube videos</em> about system architecture</li>
                    <li class="fragment">Read <em>engineering blogs</em> (Netflix, Uber, Google)</li>
                    <li class="fragment">Study how <em>real systems</em> are built</li>
                </ul>
            </section>
            <section>
                <p>Use a <em>waterfall approach</em> to structure your answer</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>The System Design Process</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>1. Requirements</em> - What are we building?</li>
                    <li class="fragment"><em>2. Design</em> - How will we build it?</li>
                    <li class="fragment"><em>3. Implementation</em> - Code or SQL examples</li>
                    <li class="fragment"><em>4. Testing</em> - How do we verify it works?</li>
                    <li class="fragment"><em>5. Release</em> - How do we deploy it?</li>
                </ul>
            </section>
            <section>
                <p><em>Step 1: Requirements</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Identify use cases</h3>
                <ul>
                    <li class="fragment"><em>Who</em> are the users?</li>
                    <li class="fragment"><em>What</em> do they need to do?</li>
                    <li class="fragment"><em>Why</em> do they need to do it?</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Identify essential qualities</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Scalability</em> - Handle growing users/data</li>
                    <li class="fragment"><em>Availability</em> - System uptime (99.9%?)</li>
                    <li class="fragment"><em>Consistency</em> - Data accuracy</li>
                    <li class="fragment"><em>Performance</em> - Response time requirements</li>
                    <li class="fragment"><em>Security</em> - Data protection needs</li>
                </ul>
            </section>
            <section>
                <p><em>Step 2: Design</em></p>
            </section>
            <section>
                <p>Think about <em>software qualities</em> when designing</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Robustness & Reliability</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Error handling</em> - Graceful failures</li>
                    <li class="fragment"><em>Logging</em> - Track issues</li>
                    <li class="fragment"><em>Load balancing</em> - Distribute traffic</li>
                    <li class="fragment"><em>Co-location</em> - Reduce latency</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Security considerations</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Cross-site scripting (XSS)</em> - Input sanitization</li>
                    <li class="fragment"><em>Click hijacking</em> - Frame busting</li>
                    <li class="fragment"><em>Spam prevention</em> - Rate limiting, CAPTCHAs</li>
                    <li class="fragment"><em>DDoS protection</em> - CDN, firewalls</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Scalability techniques</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Distributed systems</em> - Multiple servers</li>
                    <li class="fragment"><em>Sharding</em> - Split database horizontally</li>
                    <li class="fragment"><em>Caching</em> - Redis, Memcached</li>
                    <li class="fragment"><em>Replication</em> - Multiple database copies</li>
                </ul>
            </section>
            <section>
                <p>Start with the <em>high-level overview</em>, then go deep</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Use outlines to structure your design</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Front-end</em> - User interface layer</li>
                    <li class="fragment"><em>Middle-tier</em> - Application/business logic</li>
                    <li class="fragment"><em>Back-end</em> - Data storage and processing</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Example: Video streaming service</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Front-end</em>
                        <ul>
                            <li>Load-balanced web servers in major markets</li>
                            <li>CDN for video content delivery</li>
                        </ul>
                    </li>
                    <li class="fragment"><em>Middle-tier</em>
                        <ul>
                            <li>Authentication service API</li>
                            <li>Video upload/processing service</li>
                            <li>Streaming service API</li>
                        </ul>
                    </li>
                    <li class="fragment"><em>Back-end</em>
                        <ul>
                            <li>Video storage (S3, Cloud Storage)</li>
                            <li>Metadata database (user info, video data)</li>
                        </ul>
                    </li>
                </ul>
            </section>
            <section>
                <p><em>Step 3: Implementation</em></p>
            </section>
            <section>
                <p>You may be asked to <em>code part of your solution</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Implementation examples</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Write a <em>URL shortening</em> algorithm</li>
                    <li class="fragment">Design a <em>database schema</em> (SQL)</li>
                    <li class="fragment">Implement a <em>cache eviction</em> policy (LRU)</li>
                    <li class="fragment">Write a <em>consistent hashing</em> function</li>
                </ul>
            </section>
            <section>
                <p><em>Step 4: Testing & Verifiability</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>How will you verify it works?</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">What would you <em>log</em> and why?</li>
                    <li class="fragment">How would you configure <em>alerting</em>?</li>
                    <li class="fragment">What <em>metrics</em> would you track?</li>
                    <li class="fragment"><em>Continuous integration</em> testing strategy</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Important metrics to monitor</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Latency</em> - Response time (p50, p95, p99)</li>
                    <li class="fragment"><em>Throughput</em> - Requests per second</li>
                    <li class="fragment"><em>Error rate</em> - Percentage of failed requests</li>
                    <li class="fragment"><em>CPU/Memory usage</em> - Resource consumption</li>
                    <li class="fragment"><em>Database performance</em> - Query time, connection pool</li>
                </ul>
            </section>
            <section>
                <p><em>Step 5: Release</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Deployment strategy</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Testing environment</em> - Validate changes</li>
                    <li class="fragment"><em>Staging environment</em> - Production-like testing</li>
                    <li class="fragment"><em>Production environment</em> - Live system</li>
                    <li class="fragment"><em>Blue-green deployment</em> - Zero-downtime releases</li>
                    <li class="fragment"><em>Canary releases</em> - Gradual rollout</li>
                </ul>
            </section>
            <section>
                <p>Practice <em>back-of-the-napkin calculations</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Key numbers to know</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>1 KB</em> = 1,024 bytes (2<sup>10</sup>)</li>
                    <li class="fragment"><em>1 MB</em> = 1,048,576 bytes (2<sup>20</sup>)</li>
                    <li class="fragment"><em>1 GB</em> = 1,073,741,824 bytes (2<sup>30</sup>)</li>
                    <li class="fragment"><em>86,400 seconds</em> in a day</li>
                    <li class="fragment"><em>2.6 million seconds</em> in a month</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Example calculation</h3>
                <p style=${{"font-size": ".85em"}}><em>Question:</em> Twitter has 300M daily active users. Each user tweets 2 times per day on average. How many tweets per second?</p>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment">Total tweets per day = 300M × 2 = 600M</li>
                    <li class="fragment">Tweets per second = 600M ÷ 86,400 ≈ 7,000 tweets/sec</li>
                    <li class="fragment">Peak traffic (3x) ≈ 21,000 tweets/sec</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Always ask clarifying questions</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">How many <em>users</em> do we expect?</li>
                    <li class="fragment">What's the expected <em>read/write ratio</em>?</li>
                    <li class="fragment">What's the <em>data size</em> per user?</li>
                    <li class="fragment">What's the expected <em>response time</em>?</li>
                    <li class="fragment">What are the <em>availability requirements</em>?</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Key takeaways</h3>
                <ul style=${{"font-size": ".82em"}}>
                    <li class="fragment">Design interviews test your <em>problem-solving</em> and <em>communication</em></li>
                    <li class="fragment">Master <em>SOLID principles</em> for coding design</li>
                    <li class="fragment">Know your <em>data structures</em> and <em>collections</em></li>
                    <li class="fragment">Use a <em>structured approach</em> for system design</li>
                    <li class="fragment">Practice <em>calculations</em> and <em>estimations</em></li>
                    <li class="fragment">Always <em>ask questions</em> and <em>clarify requirements</em></li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Resources to practice</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Books</em>: "Designing Data-Intensive Applications", "System Design Interview"</li>
                    <li class="fragment"><em>YouTube</em>: Tech Dummies, Gaurav Sen, System Design Interview</li>
                    <li class="fragment"><em>Websites</em>: LeetCode, HackerRank, Pramp</li>
                    <li class="fragment"><em>Practice</em> with peers and mentors</li>
                </ul>
            </section>
        <//>`;
}

export default Supplemental9;
