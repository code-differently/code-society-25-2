# Lesson 20 - Interactive UI Components

This project implements three mobile-responsive UI components using only HTML, CSS, and JavaScript (no frameworks or libraries).

## Components Implemented

### 1. Tabbed Component
- **Features:**
  - Three interactive tabs with smooth transitions
  - Keyboard navigation support (arrow keys)
  - Mobile-responsive design (stacked on small screens)
  - Fade-in animation when switching tabs
  - ARIA accessibility considerations

### 2. Accordion Component
- **Features:**
  - Three collapsible sections
  - Smooth expand/collapse animations
  - Multiple sections can be open simultaneously
  - Rotating plus/minus icon indicators
  - Hover effects and active states
  - Mobile-friendly touch targets

### 3. Rotating Photo Gallery
- **Features:**
  - Shows 3 photos on desktop, 2 on tablet, 1 on mobile
  - Navigation buttons (previous/next)
  - Dot indicators for direct navigation
  - Auto-rotation every 3 seconds
  - Pause on hover or user interaction
  - Touch/swipe support for mobile devices
  - Keyboard navigation (arrow keys)
  - Smooth slide transitions

## Technical Implementation

### HTML Structure
- Semantic HTML5 elements
- Proper accessibility attributes
- Mobile-first responsive design
- Clean, organized markup

### CSS Features
- CSS Grid and Flexbox for layout
- CSS transitions and animations
- Mobile-responsive breakpoints
- Modern color scheme and typography
- Hover and focus states
- Smooth animations

### JavaScript Functionality
- Pure vanilla JavaScript (ES6+)
- Event delegation and proper event handling
- Touch/swipe gesture support
- Keyboard navigation
- Auto-rotation with pause functionality
- Window resize handling
- Accessibility considerations

## File Structure
```
trishtanhusser/
├── index.html          # Main HTML file
├── styles.css          # All CSS styles
├── script.js           # JavaScript functionality
└── README.md           # This documentation
```

## How to Run
1. Open `index.html` in any modern web browser
2. The page works completely offline (no internet required)
3. Test on different screen sizes to see responsive behavior

## Browser Compatibility
- Chrome (recommended)
- Firefox
- Safari
- Edge
- Mobile browsers (iOS Safari, Chrome Mobile)

## Features Implemented
- ✅ Tabbed component with 3 tabs
- ✅ Accordion component with 3 sections
- ✅ Photo gallery showing up to 3 pictures
- ✅ Mobile-responsive design
- ✅ No external libraries or frameworks
- ✅ Works without internet access
- ✅ Keyboard navigation support
- ✅ Touch/swipe support for mobile
- ✅ Auto-rotation with pause functionality
- ✅ Smooth animations and transitions

## Accessibility Features
- Keyboard navigation support
- Focus indicators
- Semantic HTML structure
- Proper button and link roles
- Color contrast considerations

## Future Enhancements
- Add ARIA labels for better screen reader support
- Implement lazy loading for gallery images
- Add more animation options
- Include unit tests

## Stretch Assignment - Tic Tac Toe 🎮

**Additional Implementation:** Complete Tic Tac Toe game with advanced features

### Features Implemented
- **Game Modes:**
  - Human vs Human
  - Human vs AI with 3 difficulty levels (Easy, Medium, Hard)
- **AI Intelligence:**
  - Easy: Random moves
  - Medium: 70% optimal moves, 30% random
  - Hard: Perfect minimax-style strategy
- **Game Features:**
  - Score tracking with local storage persistence
  - Animated game board and pieces
  - Modal dialogs for game results
  - Sound effects using Web Audio API
  - Keyboard controls (1-9 for moves, R for reset, Esc to close modal)
  - Mobile-responsive design
  - Winning combination highlighting

### Tic Tac Toe Files
```
trishtanhusser/
├── tictactoe.html      # Tic Tac Toe game page
├── tictactoe.css       # Game-specific styles
├── tictactoe.js        # Complete game logic with AI
└── ...other files
```

### How to Play
1. Open `tictactoe.html` in a web browser
2. Choose game mode (Human vs Human or Human vs AI)
3. If playing against AI, select difficulty level
4. Click cells or use number keys (1-9) to make moves
5. Scores are automatically saved and persistent across sessions

### Technical Highlights
- Object-oriented JavaScript design
- Local storage for score persistence
- Web Audio API for sound effects
- Advanced AI algorithms (minimax strategy for hard mode)
- Complete keyboard navigation support
- Responsive design for all screen sizes
