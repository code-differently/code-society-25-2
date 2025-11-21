import htm from "htm";
import { createElement } from "react";
import { CodeSlide, DemoSlide, Lesson, QuestionsSlide } from "../Layouts/index.js";

const html = htm.bind(createElement);

function Supplemental7() {
    return html`
        <${Lesson} title="Web Accessibility" lessonId="supplemental_7" subtitle="Supplemental 7">
            <section>
                <p>The web should be <em>accessible to everyone</em>, regardless of their abilities.</p>
            </section>
            <section>
                <p><em>Web accessibility</em> means designing and building websites that people with disabilities can use.</p>
            </section>
            <section>
                <p>This isn't just <em>nice to have</em>—it's a <em>legal requirement</em> and <em>ethical responsibility</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Why accessibility matters</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Legal compliance</em> - ADA, Section 508, WCAG standards</li>
                    <li class="fragment"><em>Market reach</em> - 1 in 4 US adults has a disability</li>
                    <li class="fragment"><em>Better UX</em> - Accessible design benefits everyone</li>
                    <li class="fragment"><em>SEO benefits</em> - Better structure improves search rankings</li>
                </ul>
            </section>
            <section>
                <p>Let's look at the <em>types of impairments</em> that require accessible tools.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Visual impairments</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Blindness</em> - Complete inability to see</li>
                    <li class="fragment"><em>Low vision</em> - Partial sight, difficulty with contrast</li>
                    <li class="fragment"><em>Color blindness</em> - Affects ~8% of men, ~0.5% of women</li>
                    <li class="fragment"><em>Photosensitivity</em> - Seizures from flashing content</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Auditory impairments</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Deafness</em> - Complete inability to hear</li>
                    <li class="fragment"><em>Hard of hearing</em> - Partial hearing loss</li>
                    <li class="fragment"><em>Auditory processing disorders</em> - Difficulty processing audio</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Motor/Physical impairments</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Limited dexterity</em> - Arthritis, tremors, paralysis</li>
                    <li class="fragment"><em>Mobility impairments</em> - Limited use of hands/arms</li>
                    <li class="fragment"><em>Temporary injuries</em> - Broken bones, RSI</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Cognitive impairments</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Learning disabilities</em> - Dyslexia, dyscalculia</li>
                    <li class="fragment"><em>Attention disorders</em> - ADHD, difficulty focusing</li>
                    <li class="fragment"><em>Memory impairments</em> - Short-term memory issues</li>
                    <li class="fragment"><em>Neurological conditions</em> - Autism, epilepsy</li>
                </ul>
            </section>
            <section>
                <p>Don't forget <em>situational impairments</em>—bright sunlight, noisy environments, holding a baby.</p>
            </section>
            <section>
                <p>Now let's explore how people with disabilities <em>interact with technology</em>.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Screen readers</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>JAWS</em> - Windows (commercial)</li>
                    <li class="fragment"><em>NVDA</em> - Windows (free)</li>
                    <li class="fragment"><em>VoiceOver</em> - macOS/iOS (built-in)</li>
                    <li class="fragment"><em>TalkBack</em> - Android (built-in)</li>
                </ul>
            </section>
            <section>
                <p>Screen readers convert <em>text and UI elements</em> to speech or Braille.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Alternative input devices</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>Speech recognition</em> - Dragon, Voice Control</li>
                    <li class="fragment"><em>Switch devices</em> - Binary input for navigation</li>
                    <li class="fragment"><em>Eye-tracking</em> - Tobii, EyeGaze systems</li>
                    <li class="fragment"><em>Adaptive keyboards</em> - Large keys, one-handed layouts</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Other assistive technologies</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Screen magnification</em> - ZoomText, OS magnifiers</li>
                    <li class="fragment"><em>Braille displays</em> - Refreshable tactile output</li>
                    <li class="fragment"><em>Keyboard navigation</em> - Tab, arrow keys, shortcuts</li>
                    <li class="fragment"><em>Captions & transcripts</em> - For audio/video content</li>
                </ul>
            </section>
            <section>
                <p>So how do we make our websites <em>accessible</em>?</p>
            </section>
            <section>
                <p>Follow the <em>WCAG</em> principles: <em>POUR</em></p>
            </section>
            <section class="ml-bullet-slide">
                <h3>WCAG POUR principles</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Perceivable</em> - Users can perceive the information</li>
                    <li class="fragment"><em>Operable</em> - Users can operate the interface</li>
                    <li class="fragment"><em>Understandable</em> - Information is clear and usable</li>
                    <li class="fragment"><em>Robust</em> - Works with current and future technologies</li>
                </ul>
            </section>
            <section>
                <p>Let's start with <em>semantic HTML</em>—the foundation of accessibility.</p>
            </section>
            <${CodeSlide} lang="html" badge="Bad ❌" fontSize=".35em" lineNumbers=true>
${`
<!-- Don't do this -->
<div class="header">
  <div class="nav">
    <div onclick="navigate()">Home</div>
    <div onclick="navigate()">About</div>
  </div>
</div>
<div class="content">
  <div class="heading">Welcome</div>
  <div>This is my website.</div>
</div>
`}
            <//>
            <${CodeSlide} lang="html" badge="Good ✅" fontSize=".35em" lineNumbers=true>
${`
<!-- Use semantic HTML -->
<header>
  <nav>
    <a href="/">Home</a>
    <a href="/about">About</a>
  </nav>
</header>
<main>
  <h1>Welcome</h1>
  <p>This is my website.</p>
</main>
`}
            <//>
            <section>
                <p>Use proper <em>heading hierarchy</em> (h1-h6) to structure content logically.</p>
            </section>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".35em" lineNumbers=true>
${`
<h1>Main Page Title</h1>

<h2>Section 1</h2>
<p>Content for section 1...</p>

<h3>Subsection 1.1</h3>
<p>More details...</p>

<h2>Section 2</h2>
<p>Content for section 2...</p>
`}
            <//>
            <section>
                <p><em>Alt text</em> is critical for images.</p>
            </section>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".37em" lineNumbers="1-2|4-5|7-8">
${`
<!-- Descriptive alt text for content images -->
<img src="cat.jpg" alt="Orange tabby cat sleeping on windowsill">

<!-- Empty alt for decorative images -->
<img src="divider.png" alt="" role="presentation">

<!-- Avoid generic alt text -->
<img src="photo.jpg" alt="image"> <!-- ❌ Bad -->
`}
            <//>
            <section>
                <p>All interactive elements must be <em>keyboard accessible</em>.</p>
            </section>
            <${CodeSlide} lang="html" badge="Bad ❌" fontSize=".37em" lineNumbers=true>
${`
<!-- Don't do this - divs aren't keyboard accessible -->
<div onclick="submitForm()">Submit</div>
`}
            <//>
            <${CodeSlide} lang="html" badge="Good ✅" fontSize=".37em" lineNumbers=true>
${`
<!-- Use proper button element -->
<button onclick="submitForm()">Submit</button>

<!-- Or use a link for navigation -->
<a href="/submit">Submit</a>
`}
            <//>
            <section>
                <p>Always provide <em>visible focus indicators</em> so users know where they are.</p>
            </section>
            <${CodeSlide} lang="css" badge="CSS" fontSize=".37em" lineNumbers=true>
${`
/* Don't remove focus outlines! */
button:focus {
  outline: 3px solid #4A90E2;
  outline-offset: 2px;
}

/* NEVER do this */
*:focus {
  outline: none; /* ❌ Bad */
}
`}
            <//>
            <section>
                <p><em>Color contrast</em> is essential for readability.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>WCAG contrast requirements</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>Normal text</em> - 4.5:1 contrast ratio (AA)</li>
                    <li class="fragment"><em>Large text</em> - 3:1 contrast ratio (AA)</li>
                    <li class="fragment"><em>Enhanced</em> - 7:1 for normal text (AAA)</li>
                    <li class="fragment"><em>Don't use color alone</em> to convey information</li>
                </ul>
            </section>
            <${CodeSlide} lang="html" badge="Bad ❌" fontSize=".35em" lineNumbers=true>
${`
<!-- Using only color to indicate required fields -->
<style>
  .required { color: red; }
</style>
<label class="required">Email</label>
<input type="email">
`}
            <//>
            <${CodeSlide} lang="html" badge="Good ✅" fontSize=".35em" lineNumbers=true>
${`
<!-- Use multiple indicators -->
<label>
  Email <span aria-label="required">*</span>
</label>
<input type="email" required aria-required="true">
`}
            <//>
            <section>
                <p>Form labels must be <em>properly associated</em> with inputs.</p>
            </section>
            <${CodeSlide} lang="html" badge="Bad ❌" fontSize=".37em" lineNumbers=true>
${`
<!-- Don't do this -->
<div>Username</div>
<input type="text">
`}
            <//>
            <${CodeSlide} lang="html" badge="Good ✅" fontSize=".37em" lineNumbers=true>
${`
<!-- Use proper label association -->
<label for="username">Username</label>
<input type="text" id="username" name="username">

<!-- Or nest the input -->
<label>
  Username
  <input type="text" name="username">
</label>
`}
            <//>
            <section>
                <p>Now let's talk about <em>ARIA</em>—Accessible Rich Internet Applications.</p>
            </section>
            <section>
                <p>ARIA provides <em>additional semantics</em> when HTML alone isn't enough.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common ARIA attributes</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>role</em> - Define element purpose (button, navigation, alert)</li>
                    <li class="fragment"><em>aria-label</em> - Provide accessible name</li>
                    <li class="fragment"><em>aria-labelledby</em> - Reference label by ID</li>
                    <li class="fragment"><em>aria-describedby</em> - Additional description</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>More ARIA attributes</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment"><em>aria-live</em> - Announce dynamic content changes</li>
                    <li class="fragment"><em>aria-hidden</em> - Hide decorative elements</li>
                    <li class="fragment"><em>aria-expanded</em> - Indicate collapse/expand state</li>
                    <li class="fragment"><em>aria-checked</em> - Communicate checkbox state</li>
                </ul>
            </section>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".35em" lineNumbers=true>
${`
<!-- Custom button with ARIA -->
<div role="button" 
     tabindex="0"
     aria-label="Close dialog"
     onclick="closeDialog()"
     onkeypress="handleKey(event)">
  ×
</div>
`}
            <//>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".35em" lineNumbers=true>
${`
<!-- Expandable section with ARIA -->
<button aria-expanded="false" 
        aria-controls="content-1"
        onclick="toggleContent()">
  Show More
</button>
<div id="content-1" hidden>
  <p>Hidden content...</p>
</div>
`}
            <//>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".35em" lineNumbers=true>
${`
<!-- Live region for dynamic updates -->
<div aria-live="polite" aria-atomic="true">
  <p>Items in cart: <span id="cart-count">0</span></p>
</div>

<script>
  // Screen reader will announce changes
  document.getElementById('cart-count').textContent = '3';
</script>
`}
            <//>
            <section>
                <p><span style=${{"color": "red"}}>IMPORTANT!</span><br />Use ARIA only when semantic HTML isn't enough.</p>
            </section>
            <section>
                <p>The first rule of ARIA: <em>"Don't use ARIA"</em> if HTML works.</p>
            </section>
            <${CodeSlide} lang="html" badge="Over-using ARIA ❌" fontSize=".37em" lineNumbers=true>
${`
<!-- Don't do this -->
<div role="button" tabindex="0" aria-label="Submit">
  Submit
</div>
`}
            <//>
            <${CodeSlide} lang="html" badge="Use semantic HTML ✅" fontSize=".37em" lineNumbers=true>
${`
<!-- Just use a button -->
<button>Submit</button>
`}
            <//>
            <section>
                <p>Let's look at <em>testing tools</em> to find accessibility issues.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Automated testing tools</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>axe DevTools</em> - Browser extension</li>
                    <li class="fragment"><em>Lighthouse</em> - Built into Chrome DevTools</li>
                    <li class="fragment"><em>WAVE</em> - Web Accessibility Evaluation Tool</li>
                    <li class="fragment"><em>Pa11y</em> - Command-line testing</li>
                </ul>
            </section>
            <section>
                <p>Automated tools only catch <em>~30% of issues</em>. Manual testing is crucial.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Manual testing methods</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment">Navigate with <em>keyboard only</em> (unplug mouse)</li>
                    <li class="fragment">Test with <em>screen readers</em> (VoiceOver, NVDA)</li>
                    <li class="fragment">Zoom to <em>200%</em> in browser</li>
                    <li class="fragment">Check <em>color contrast</em> with tools</li>
                    <li class="fragment">Test with <em>real users</em> who have disabilities</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Quick accessibility wins</h3>
                <ul style=${{"font-size": ".85em"}}>
                    <li class="fragment">Add <em>alt text</em> to all images</li>
                    <li class="fragment">Use proper <em>heading hierarchy</em></li>
                    <li class="fragment">Label all <em>form inputs</em></li>
                    <li class="fragment">Ensure <em>keyboard navigation</em> works</li>
                    <li class="fragment">Check <em>color contrast</em></li>
                    <li class="fragment">Add <em>skip links</em> for navigation</li>
                </ul>
            </section>
            <section class="ml-bullet-slide">
                <h3>Common mistakes to avoid</h3>
                <ul style=${{"font-size": ".82em"}}>
                    <li class="fragment">Using divs/spans instead of semantic elements</li>
                    <li class="fragment">Missing or poor alt text</li>
                    <li class="fragment">Insufficient color contrast</li>
                    <li class="fragment">Keyboard traps in modals</li>
                    <li class="fragment">Removing focus indicators</li>
                    <li class="fragment">Auto-playing audio/video</li>
                </ul>
            </section>
            <section>
                <p>Let's see a <em>skip link</em> in action—it helps keyboard users bypass navigation.</p>
            </section>
            <${CodeSlide} lang="html" badge="HTML" fontSize=".33em" lineNumbers=true>
${`
<!-- Skip link at top of page -->
<a href="#main-content" class="skip-link">
  Skip to main content
</a>

<nav>
  <!-- Navigation links -->
</nav>

<main id="main-content">
  <h1>Page Title</h1>
  <!-- Main content -->
</main>
`}
            <//>
            <${CodeSlide} lang="css" badge="CSS" fontSize=".37em" lineNumbers=true>
${`
/* Hide skip link until focused */
.skip-link {
  position: absolute;
  top: -40px;
  left: 0;
  background: #000;
  color: #fff;
  padding: 8px;
  text-decoration: none;
}

.skip-link:focus {
  top: 0;
}
`}
            <//>
            <section>
                <p>Accessibility is <em>not optional</em>—it's a fundamental part of web development.</p>
            </section>
            <section>
                <p>Build accessibility in from the <em>start</em>, not as an afterthought.</p>
            </section>
            <section class="ml-bullet-slide">
                <h3>Helpful resources</h3>
                <ul style=${{"font-size": ".9em"}}>
                    <li class="fragment"><em>W3C WAI</em> - Web Accessibility Initiative</li>
                    <li class="fragment"><em>WebAIM</em> - Web Accessibility In Mind</li>
                    <li class="fragment"><em>A11Y Project</em> - Community-driven resources</li>
                    <li class="fragment"><em>ARIA Authoring Practices</em> - Implementation patterns</li>
                </ul>
            </section>
            <${DemoSlide} />
            <${QuestionsSlide}/>
        <//>`;
}

export default Supplemental7;
