# Lesson 20: Web FE: Intro to HTML, CSS, & JS ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/lesson_20))

## Pre-work

### Required

* [HTML in 5 minutes (Video)](https://www.youtube.com/watch?v=salY_Sm6mv4)
* [CSS in 5 minutes (Video)](https://www.youtube.com/watch?v=Z4pCqK-V_Wo)
* [Chrome DevTools Crash Course (Video)](https://www.youtube.com/watch?v=151NXMk0a2c)

### Recommended

* [web.dev Learn CSS series (Articles)](https://web.dev/learn/css/)
* [CSS Tutorial – Full Course for Beginners (Video)](https://www.youtube.com/watch?v=OXGznpKZ_sA&t=68s)

## Homework

- [ ] [Making HTML interactive with JavaScript](#making-html-interactive-with-javascript)
- [ ] Do pre-work for [lesson 21](/lesson_21/).

## Making HTML interactive with JavaScript

### Assignment Requirements
Create an HTML page that features an implementation of three mobile-responsive UI controls:

1. **Tabbed Component** - Switch between different content panels
2. **Accordion Component** - Expandable/collapsible content sections  
3. **Rotating Photo Gallery** - Shows up to three pictures at a time

**Requirements:**
- MUST ONLY use HTML, CSS, and JavaScript
- Cannot use any existing libraries or frameworks
- Must work without network or internet access
- No animations required but welcome

### Implementation Features

#### 📱 Mobile Responsive Design
- **Desktop**: Shows 3 gallery items, full tab layout
- **Tablet**: Shows 2 gallery items, scrollable tabs if needed
- **Mobile**: Shows 1 gallery item, stacked layout

#### 🎯 Tabbed Component
- Click tabs to switch content
- Keyboard navigation (Arrow keys, Home, End)
- Smooth transitions
- Accessible with ARIA labels

#### 📋 Accordion Component
- Click headers to expand/collapse content
- Only one section open at a time
- Keyboard support (Enter, Space)
- Animated transitions
- Visual indicators (+ / ×)

#### 🖼️ Photo Gallery
- Shows 3 items on desktop, 2 on tablet, 1 on mobile
- Navigation buttons (Previous/Next)
- Dot indicators for quick navigation
- Auto-rotation (pauses on hover)
- Touch/swipe support for mobile
- Keyboard navigation (Arrow keys)
- Continuous rotation (loops back to start)

### Files Structure
```
lesson_20/
├── index.html      # Main HTML structure
├── styles.css      # All styling and responsive design
├── script.js       # Interactive functionality
└── README.md       # This documentation
```

### How to Use

1. Open `index.html` in a web browser
2. **Tabs**: Click tab buttons or use arrow keys
3. **Accordion**: Click headers to expand/collapse
4. **Gallery**: Use navigation buttons, dots, or swipe on mobile

### Live Preview

To run with live preview:
1. Install Live Server extension in VS Code
2. Right-click on `index.html`
3. Select "Open with Live Server"

## Making HTML interactive with JavaScript

For this assignment, you will create an HTML page that features an implementation of three mobile-responsive UI controls:

1) Tabbed component
2) Accordion component
3) Rotating photo gallery that shows up to three pictures at a time

You MUST ONLY use HTML, CSS, and JavaScript for your implementation, and you cannot use any existing libraries or frameworks. Your demo must be able to run without network or internet access. There is no need to implement animations, but you are welcome to do so.

## Stretch assignment - Tic Tac Toe

Please implement Tic Tac Toe from scratch. Similar to above, you must not use any existing libraries or frameworks.