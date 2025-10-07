# Lesson 21 - Express Web Server

This project implements a web server using Express.js and Node.js, featuring the Code Differently website from lesson 19 with an added contact form.

## Features

- **Static Website**: Serves the Code Differently homepage with styling and images
- **Contact Form**: A functional contact form that accepts user input
- **HTTP POST Handling**: Processes form submissions and displays confirmation
- **Express.js**: Modern web framework for Node.js
- **Static File Serving**: Serves CSS, images, and other static assets

## Project Structure

```
trishtanhusser/
├── server.js          # Main Express server file
├── index.html         # Homepage (from lesson 19)
├── contact.html       # Contact form page
├── style.css          # Stylesheet (from lesson 19)
├── logo.png          # Code Differently logo
├── hero.jpg          # Hero section background image
├── package.json      # Node.js dependencies and scripts
└── README.md         # This file
```

## Installation & Setup

1. Navigate to the project directory:
   ```bash
   cd lesson_21/trishtanhusser
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the server:
   ```bash
   npm start
   ```

4. Open your browser and visit: `http://localhost:3000`

## Usage

### Homepage
- Visit `http://localhost:3000` to see the Code Differently homepage
- Navigate using the header menu

### Contact Form
- Click "Contact" in the navigation or visit `http://localhost:3000/contact`
- Fill out the form with:
  - Full Name (required)
  - Email Address (required)
  - Subject (required)
  - Message (required)
- Submit the form to see a confirmation page with your submitted data

### Form Submission
The contact form performs an HTTP POST request to `/contact` and displays:
- A thank you message
- The submitted form data
- Links to return home or submit another message

## Technical Details

- **Server**: Express.js running on port 3000 (configurable via PORT environment variable)
- **Form Processing**: Uses `express.urlencoded()` middleware to parse form data
- **Static Files**: Served from the project root directory
- **Routes**:
  - `GET /` - Homepage
  - `GET /contact` - Contact form
  - `POST /contact` - Form submission handler

## Development Notes

- Form submissions are logged to the console
- In a production environment, you would typically save form data to a database
- The server includes basic error handling and CORS support
- All static assets (CSS, images) are served directly by Express

## Port Forwarding (VS Code Dev Container)

If you're using a VS Code dev container, you may need to forward port 3000 to access the web server. See the [VS Code port forwarding documentation](https://code.visualstudio.com/docs/editor/port-forwarding) for instructions.
