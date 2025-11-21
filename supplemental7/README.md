# Supplemental 7: Web Accessibility ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/supplemental_7))

## Overview

This lesson explores web accessibility standards and implementation, covering why accessibility matters, how people with disabilities interact with technology, and practical techniques for building inclusive web applications.

## Pre-work

Please review the following resources before lecture:

### Recommended
* [Web Accessibility: ADA Compliance Tips to Design for All Users (Video)](https://www.youtube.com/watch?v=zoAFBJl9DHQ)
* [Introduction to Web Accessibility (Article)](https://www.w3.org/WAI/fundamentals/accessibility-intro/)
* [WCAG at a Glance (Quick Reference)](https://www.w3.org/WAI/standards-guidelines/wcag/glance/)

## Running the Demo

The `accessibility` directory contains an interactive Next.js application demonstrating common accessibility patterns and anti-patterns.

### Setup and Run

1. Navigate to the demo directory:
   ```bash
   cd supplemental7/a11y
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Open [http://localhost:3000](http://localhost:3000) in your browser

### Features

The demo includes 15 interactive examples comparing inaccessible ("Don't") and accessible ("Do") implementations:
- Interactive Elements (Buttons)
- Images and Alt Text
- Form Labels
- Color Contrast
- Link Text
- Heading Hierarchy
- Focus Indicators
- ARIA Labels
- Form Error Handling
- Data Tables
- Required Fields
- Decorative Images
- Select Dropdowns
- Lists
- Loading States

Each example includes:
- Live interactive demonstrations
- Code samples
- Dark mode support
- Accessibility testing tips

### Testing

The demo includes comprehensive accessibility testing. See [TESTING.md](./a11y/TESTING.md) for details.

**Quick test commands:**
```bash
cd supplemental7/a11y

# Run unit tests
npm test

# Run E2E accessibility tests
npm run test:e2e

# Run automated accessibility scan
npm run test:a11y
```

**Test coverage includes:**
- Automated WCAG 2.1 AA compliance checking
- Keyboard navigation testing
- Screen reader compatibility
- Color contrast validation
- Form accessibility
- Dark mode accessibility

## Topics Covered

### Why Accessibility Matters
- Legal compliance (ADA, Section 508, WCAG)
- Business case and market reach
- Ethical responsibility and social impact
- SEO benefits and improved user experience

### Types of Impairments
- **Visual**: Blindness, low vision, color blindness, photosensitivity
- **Auditory**: Deafness, hard of hearing, auditory processing disorders
- **Motor/Physical**: Limited dexterity, mobility impairments, temporary injuries
- **Cognitive**: Learning disabilities, attention disorders, memory impairments
- **Situational**: Environmental factors affecting ability

### Assistive Technologies
- Screen readers (JAWS, NVDA, VoiceOver, TalkBack)
- Alternative input devices (speech recognition, switches, eye-tracking)
- Screen magnification and Braille displays
- Keyboard navigation and captions

### Web Accessibility Standards

#### WCAG POUR Principles
- **Perceivable**: Information presentable to all users
- **Operable**: Interface usable by all users
- **Understandable**: Clear information and operation
- **Robust**: Compatible with current and future technologies

#### Implementation Techniques
- Semantic HTML and proper heading hierarchy
- Alt text and text alternatives
- Keyboard accessibility and focus indicators
- Color contrast and color independence
- Form labels and error handling
- ARIA (Accessible Rich Internet Applications)
- Skip links and navigation structure

### Testing and Tools
- Automated testing (axe DevTools, Lighthouse, WAVE, Pa11y)
- Manual testing methods (keyboard navigation, screen readers)
- User testing with people who have disabilities

## Related Lessons

- Lesson 23 - Intro to HTML, CSS, and JS
- Lesson 22 - Building Applications

## Tools

### Browser Extensions
* [axe DevTools](https://www.deque.com/axe/devtools/) - Accessibility testing
* [WAVE](https://wave.webaim.org/extension/) - Web Accessibility Evaluation Tool
* [Lighthouse](https://developer.chrome.com/docs/lighthouse/) - Built into Chrome DevTools

### Screen Readers
* [NVDA](https://www.nvaccess.org/) - Free screen reader for Windows
* VoiceOver - Built into macOS/iOS (Cmd+F5 to activate)
* TalkBack - Built into Android

### Testing Tools
* [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/) - Check color contrast
* [Pa11y](https://pa11y.org/) - Command-line accessibility testing
* [Color Oracle](https://colororacle.org/) - Color blindness simulator

## Additional Resources

### Standards and Guidelines
- [W3C Web Accessibility Initiative (WAI)](https://www.w3.org/WAI/)
- [WCAG 2.2 Guidelines](https://www.w3.org/WAI/WCAG22/quickref/)
- [Section 508 Standards](https://www.section508.gov/)
- [ARIA Authoring Practices Guide](https://www.w3.org/WAI/ARIA/apg/)

### Learning Resources
- [WebAIM](https://webaim.org/) - Web Accessibility In Mind
- [A11Y Project](https://www.a11yproject.com/) - Community-driven accessibility resources
- [Inclusive Design Principles](https://inclusivedesignprinciples.org/)
- [MDN Accessibility Guide](https://developer.mozilla.org/en-US/docs/Web/Accessibility)

### Videos and Courses
- [Accessibility Fundamentals (Web.dev)](https://web.dev/learn/accessibility/)
- [Screen Reader Basics (YouTube)](https://www.youtube.com/watch?v=dEbl5jvLKGQ)
- [Deque University](https://dequeuniversity.com/) - Comprehensive accessibility training

### Articles and Blogs
- [How Screen Readers Navigate Web Pages](https://www.smashingmagazine.com/2019/02/accessibility-webinar/)
- [The Business Case for Accessibility](https://www.w3.org/WAI/business-case/)
- [Writing Great Alt Text](https://jakearchibald.com/2021/great-alt-text/)

## Quick Reference: Common Accessibility Patterns

### Semantic HTML
```html
<!-- Bad -->
<div class="button" onclick="submit()">Submit</div>

<!-- Good -->
<button onclick="submit()">Submit</button>
```

### Alt Text
```html
<!-- Content image -->
<img src="cat.jpg" alt="Orange tabby cat sleeping on windowsill">

<!-- Decorative image -->
<img src="divider.png" alt="" role="presentation">
```

### Form Labels
```html
<!-- Proper label association -->
<label for="email">Email</label>
<input type="email" id="email" name="email">
```

### Skip Links
```html
<a href="#main-content" class="skip-link">Skip to main content</a>
<nav>...</nav>
<main id="main-content">...</main>
```

### ARIA Landmarks
```html
<header role="banner">...</header>
<nav role="navigation">...</nav>
<main role="main">...</main>
<footer role="contentinfo">...</footer>
```

## Key Takeaways

1. **Accessibility is not optional** - It's a legal requirement and ethical responsibility
2. **Start from the beginning** - Build accessibility in, don't bolt it on later
3. **Use semantic HTML first** - It provides built-in accessibility
4. **Test with real users** - Automated tools only catch ~30% of issues
5. **Keyboard navigation is essential** - All functionality must work without a mouse
6. **Color contrast matters** - Ensure text is readable for everyone
7. **ARIA enhances, doesn't replace** - Use HTML semantics first, ARIA when needed
8. **Accessible design benefits everyone** - The "curb-cut effect" improves UX for all users
