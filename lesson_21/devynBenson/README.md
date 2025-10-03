# Lesson 21 Express Server - Code Differently

A modern, fully-featured Express.js web server serving the Code Differently website with Miami Vice-inspired contact form styling.

## 🚀 Features

- **Modern Homepage**: Responsive design with gradient backgrounds and smooth animations
- **Miami Vice Contact Form**: Turquoise and pink gradient styling inspired by 80s aesthetics
- **Complete Asset Structure**: Custom-created assets including SVG logo and structured CSS
- **HTTP GET & POST Routes**: Full form handling with styled response pages
- **Mobile Responsive**: Works perfectly on all device sizes

## 📁 Project Structure

```
devynbenson/
├── index.js              # Express server with routes
├── package.json          # Dependencies and scripts
├── public/               # Static assets directory
│   ├── css/
│   │   ├── style.css     # Main website styles
│   │   └── contact.css   # Miami Vice contact form styles
│   ├── images/
│   │   └── logo.svg      # Custom Code Differently logo
│   └── index.html        # Main homepage
└── README.md             # This file
```

## 🛠 Setup and Running

1. **Install dependencies:**
   ```bash
   npm install
   ```

2. **Start the server:**
   ```bash
   node index.js
   ```

3. **Open your browser:**
   - Main site: `http://localhost:3000`
   - Contact form: `http://localhost:3000/contact`

## 🌐 Routes

### GET `/`
- Serves the modern homepage with:
  - Hero section with gradient background
  - Program showcase cards
  - Responsive navigation
  - Call-to-action buttons

### GET `/contact`
- Displays Miami Vice-styled contact form with:
  - Animated gradient background (turquoise → pink)
  - Google Forms-inspired layout
  - Glassmorphism design elements
  - Required form validation

### POST `/contact`
- Processes form submissions
- Logs data to server console
- Shows styled thank you page with:
  - Miami Vice theme continuation
  - Submission details display
  - Navigation options

## 🎨 Design Features

### Main Site
- **Gradient Backgrounds**: Modern purple-blue gradients
- **Glassmorphism**: Frosted glass effects on cards
- **Smooth Animations**: Hover effects and transitions
- **Typography**: Clean, modern font stack

### Contact Form (Miami Vice Theme)
- **Animated Gradients**: Cycling through turquoise, pink, purple, blue, and green
- **Glassmorphism Effects**: Blurred transparent containers
- **Interactive Elements**: Hover animations and focus states
- **Responsive Design**: Mobile-optimized layouts

## 🔧 Technical Stack

- **Backend**: Node.js with Express.js
- **Frontend**: Vanilla HTML, CSS, JavaScript
- **Styling**: Modern CSS with gradients, animations, and responsive design
- **Assets**: Custom SVG logo and structured asset organization

## 📱 Responsive Breakpoints

- **Desktop**: 1200px+ (Full layout)
- **Tablet**: 768px-1199px (Adapted grid)
- **Mobile**: 480px-767px (Stacked layout)
- **Small Mobile**: <480px (Compact design)

## 🎯 Assignment Requirements Met

✅ **Custom Assets**: Created original HTML, CSS, and logo (not from lesson_19)  
✅ **Unique Folder**: Located in `/lesson_21/devynbenson/`  
✅ **GET Routes**: Homepage and contact form  
✅ **POST Route**: Form submission handling  
✅ **Form Styling**: Miami Vice color scheme (turquoise & pink)  
✅ **Google Forms Style**: Clean, modern form layout  
✅ **Data Display**: Shows submitted form data  
✅ **Structured Website**: Professional, well-organized design  

## 🚀 Port Configuration

Default port: `3000`

To use a different port:
```bash
PORT=8080 node index.js
```
