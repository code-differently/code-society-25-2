import htm from "htm";
import { createElement } from "react";
import { DemoSlide, Lesson } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental10() {
    return html`
        <${Lesson} title="Stripe Integration" lessonId="supplemental_10" subtitle="Supplemental 10" titleFontSize="1.2em">
            <section class="ml-bullet-slide">
                <h3>Good to review:</h3>
                <ul style=${{"font-size": ".95em"}}>
                    <li class="fragment"><i>Lesson 14</i> - Exception Handling</li>
                    <li class="fragment"><i>Lesson 19</i> - REST APIs</li>
                    <li class="fragment"><i>Lesson 22</i> - Building Applications</li>
                    <li class="fragment"><i>Supplemental 6</i> - Microservices</li>
                </ul>
            </section>
            <section>
                <p>Today we're going to talk about <em>accepting payments online</em></p>
            </section>
            <section>
                <p>Specifically, we'll learn about <em>Stripe</em>, one of the most popular payment platforms</p>
            </section>
            <section>
                <p>Why would you want to accept <em>online payments</em>?</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Revenue opportunities</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Global reach</em> - Sell to customers anywhere</li>
                    <li class="fragment"><em>24/7 sales</em> - Make money while you sleep</li>
                    <li class="fragment"><em>Instant transactions</em> - No waiting for checks</li>
                    <li class="fragment"><em>Lower overhead</em> - No physical store needed</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Customer convenience</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Multiple payment methods</em> - Cards, wallets, bank transfers</li>
                    <li class="fragment"><em>Save payment info</em> - One-click checkout</li>
                    <li class="fragment"><em>Mobile friendly</em> - Pay from anywhere</li>
                    <li class="fragment"><em>Instant confirmation</em> - No waiting</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Business advantages</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Automated billing</em> - Subscriptions, recurring payments</li>
                    <li class="fragment"><em>Better cash flow</em> - Faster payment processing</li>
                    <li class="fragment"><em>Detailed analytics</em> - Track revenue and trends</li>
                    <li class="fragment"><em>Reduced errors</em> - Automated reconciliation</li>
                    <li class="fragment"><em>Fraud protection</em> - Built-in security measures</li>
                </ul>
            </section>
            <section>
                <p>Online payments are <em>essential</em> for modern businesses</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common use cases</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>E-commerce</em> - Online stores and marketplaces</li>
                    <li class="fragment"><em>SaaS products</em> - Subscription-based software</li>
                    <li class="fragment"><em>Digital content</em> - Courses, ebooks, media</li>
                    <li class="fragment"><em>Services</em> - Consulting, freelancing, bookings</li>
                </ul>
            </section>
            <section>
                <p>If online payments are so great, why doesn't everyone do it?</p>
            </section>
            <section>
                <p>Because implementing payments is <em>surprisingly complex</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Security challenges</h3>
                <ul style=${{"font-size": ".80em"}}>
                    <li class="fragment"><em>PCI compliance</em> - Strict security standards for handling card data</li>
                    <li class="fragment"><em>Encryption</em> - Protecting sensitive information in transit</li>
                    <li class="fragment"><em>Fraud detection</em> - Identifying suspicious transactions</li>
                    <li class="fragment"><em>Data breaches</em> - One mistake can expose customer data</li>
                </ul>
            </section>
            <section>
                <p><em><i>PCI DSS</i></em> (Payment Card Industry Data Security Standard) has <i>12 requirements</i> and hundreds of sub-requirements</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Technical complexity</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Multiple payment methods</em> - Cards, wallets, bank transfers</li>
                    <li class="fragment"><em>Currency conversion</em> - Supporting international payments</li>
                    <li class="fragment"><em>Payment flows</em> - Different rules for different regions</li>
                    <li class="fragment"><em>3D Secure</em> - Strong customer authentication (SCA)</li>
                    <li class="fragment"><em>Webhooks</em> - Handling asynchronous events</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Regulatory requirements</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Different laws per country</em> - EU, US, Asia have different rules</li>
                    <li class="fragment"><em>Tax compliance</em> - Sales tax, VAT calculations</li>
                    <li class="fragment"><em>KYC requirements</em> - Know Your Customer verification</li>
                    <li class="fragment"><em>Anti-money laundering</em> - AML compliance</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Operational challenges</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Failed payments</em> - Handling declines and retries</li>
                    <li class="fragment"><em>Disputes & chargebacks</em> - Customer refund requests</li>
                    <li class="fragment"><em>Reconciliation</em> - Matching payments to orders</li>
                    <li class="fragment"><em>Customer support</em> - Helping users with payment issues</li>
                </ul>
            </section>
            <section>
                <p>Building your own payment system is like building a <em>bank</em></p>
            </section>
            <section>
                <p>That's why companies like <em>Stripe</em> exist</p>
            </section>
            <section>
                <p><em><i>Stripe</i></em> is a payment processing platform that handles the complexity for you</p>
            </section>
            <section>
                <p>Founded in <em>2010</em> by Patrick and John Collison</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>What does Stripe provide?</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Payment processing</em> - Accept cards, wallets, and more</li>
                    <li class="fragment"><em>PCI compliance</em> - Stripe handles security for you</li>
                    <li class="fragment"><em>Developer-friendly APIs</em> - RESTful with great docs</li>
                    <li class="fragment"><em>Global support</em> - 135+ currencies, 45+ countries</li>
                    <li class="fragment"><em>Fraud prevention</em> - Machine learning-based detection</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Stripe's impact</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Valued at <em>\$95 billion</em> (2024)</li>
                    <li class="fragment">Processes <em>hundreds of billions</em> annually</li>
                    <li class="fragment">Used by <em>millions</em> of businesses</li>
                    <li class="fragment">Powers companies like <em>Amazon</em>, <em>Shopify</em>, <em>Lyft</em></li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Stripe products</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Payments</em> - Accept one-time payments</li>
                    <li class="fragment"><em>Billing</em> - Manage subscriptions</li>
                    <li class="fragment"><em>Connect</em> - Build marketplaces and platforms</li>
                    <li class="fragment"><em>Radar</em> - Fraud and risk management</li>
                    <li class="fragment"><em>Terminal</em> - In-person payments</li>
                </ul>
            </section>
            <section>
                <p>The <em>Stripe API</em> is designed for developers</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Why developers love Stripe</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Great documentation</em> - Clear examples in multiple languages</li>
                    <li class="fragment"><em>Test mode</em> - Experiment without real money</li>
                    <li class="fragment"><em>Webhooks</em> - Real-time event notifications</li>
                    <li class="fragment"><em>Libraries</em> - SDKs for Node, Python, Java, Ruby, etc.</li>
                    <li class="fragment"><em>Dashboard</em> - Visual interface for monitoring</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Key Stripe concepts</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Customer</em> - Represents a buyer</li>
                    <li class="fragment"><em>Payment Method</em> - A card, bank account, or wallet</li>
                    <li class="fragment"><em>Payment Intent</em> - Tracks the payment lifecycle</li>
                    <li class="fragment"><em>Charge</em> - A completed payment</li>
                    <li class="fragment"><em>Webhook</em> - Event notification from Stripe</li>
                </ul>
            </section>
            <section>
                <p>Let's look at how <em>tokenization</em> works</p>
            </section>
            <section>
                <p><em><i>Tokenization</i></em> converts sensitive card data into a secure <i>token</i></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>How tokenization protects you</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Card data <em>never touches</em> your server</li>
                    <li class="fragment">Stripe <em>encrypts</em> the data client-side</li>
                    <li class="fragment">You only receive a <em>token</em> (e.g., tok_abc123)</li>
                    <li class="fragment">Token can only be used <em>once</em></li>
                    <li class="fragment">Reduces your <em>PCI compliance</em> burden</li>
                </ul>
            </section>
            <section>
                <p>Stripe also handles <em>3D Secure</em> authentication</p>
            </section>
            <section>
                <p><em><i>3D Secure</i></em> (3DS) adds an extra verification step for card payments</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Why 3D Secure matters</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Required in Europe</em> - Strong Customer Authentication (SCA)</li>
                    <li class="fragment"><em>Reduces fraud</em> - Confirms customer identity</li>
                    <li class="fragment"><em>Liability shift</em> - Protects merchants from chargebacks</li>
                    <li class="fragment"><em>Handled by Stripe</em> - No extra code needed</li>
                </ul>
            </section>
            <section>
                <p>Let's walk through how to <em>integrate Stripe</em> into your app</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Setup steps</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Step 1</em> - Create a Stripe account</li>
                    <li class="fragment"><em>Step 2</em> - Get API keys (test and live)</li>
                    <li class="fragment"><em>Step 3</em> - Install Stripe SDK</li>
                    <li class="fragment"><em>Step 4</em> - Build your integration</li>
                </ul>
            </section>
            <section>
                <p><em>Step 1: Create a Stripe account</em></p>
            </section>
            <section>
                <p>Go to <em>stripe.com</em> and sign up for free</p>
            </section>
            <section>
                <p>No credit card required to start testing</p>
            </section>
            <section>
                <p><em>Step 2: Get API keys</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Two types of keys</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Publishable key</em> - Used client-side (safe to expose)</li>
                    <li class="fragment"><em>Secret key</em> - Used server-side (keep secret!)</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Test vs. Live mode</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Test mode</em> - Use test cards, no real money</li>
                    <li class="fragment"><em>Live mode</em> - Real transactions, real money</li>
                    <li class="fragment">Each mode has its own <em>API keys</em></li>
                    <li class="fragment">Always start in <em>test mode</em></li>
                </ul>
            </section>
            <section>
                <p><em>Step 3: Install Stripe SDK</em></p>
            </section>
            <section>
                <h3>Node.js example</h3>
                <pre><code class="language-bash">npm install stripe</code></pre>
            </section>
            <section>
                <h3>Java example</h3>
                <pre><code class="language-xml"><dependency>
  <groupId>com.stripe</groupId>
  <artifactId>stripe-java</artifactId>
  <version>24.0.0</version>
</dependency></code></pre>
            </section>
            <section>
                <p><em>Step 4: Build your integration</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>The payment flow</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Client</em> - Collect payment details using Stripe Elements</li>
                    <li class="fragment"><em>Client</em> - Create a payment method (tokenize)</li>
                    <li class="fragment"><em>Server</em> - Create a Payment Intent with amount</li>
                    <li class="fragment"><em>Client</em> - Confirm the payment</li>
                    <li class="fragment"><em>Server</em> - Handle webhooks for payment events</li>
                </ul>
            </section>
            <${DemoSlide} />
            <section>
                <p>For subscriptions, use <em>Stripe Billing</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Subscription features</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Recurring billing</em> - Automatic charges</li>
                    <li class="fragment"><em>Trial periods</em> - Free trials before charging</li>
                    <li class="fragment"><em>Proration</em> - Handle plan changes mid-cycle</li>
                    <li class="fragment"><em>Dunning</em> - Retry failed payments automatically</li>
                    <li class="fragment"><em>Usage-based</em> - Metered billing</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Going to production</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment"><em>Complete account verification</em> - Provide business details</li>
                    <li class="fragment"><em>Switch to live keys</em> - Use production API keys</li>
                    <li class="fragment"><em>Set up webhooks</em> - Configure production endpoints</li>
                    <li class="fragment"><em>Enable Radar</em> - Turn on fraud detection</li>
                    <li class="fragment"><em>Test in production</em> - Make a real test purchase</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Key takeaways</h3>
                <ul style=${{"font-size": ".8em"}}>
                    <li class="fragment">Online payments are <em>essential</em> but <em>complex</em></li>
                    <li class="fragment">Stripe handles <em>security</em> and <em>compliance</em> for you</li>
                    <li class="fragment">Use <em>tokenization</em> to protect card data</li>
                    <li class="fragment">Follow the <em>Payment Intent</em> workflow</li>
                    <li class="fragment"><em>Webhooks</em> notify you of payment events</li>
                    <li class="fragment">Always test with <em>test cards</em> first</li>
                    <li class="fragment">Start with <em>Checkout Session</em> for simplicity</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Next steps</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Sign up for a <em>Stripe account</em></li>
                    <li class="fragment">Explore the <em>official documentation</em></li>
                    <li class="fragment">Try the <em>quickstart guides</em></li>
                    <li class="fragment">Build a <em>simple checkout</em> integration</li>
                    <li class="fragment">Experiment with <em>test mode</em></li>
                </ul>
            </section>
        <//>`;
}

export default Supplemental10;
