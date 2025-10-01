# Express Server with Signup Form - Jayden Ellis

This is a Node.js/Express web server that serves the Code Differently website with an interactive signup form. Built for lesson 21 of the Code Society program.

## Features

- **Homepage**: Displays the Code Differently homepage with information about programs
- **Signup Form**: Interactive form that accepts user input via HTTP POST
- **Dashboard**: View all user signups in a table format
- **Responsive Design**: Works on desktop and mobile devices
- **Form Validation**: Client-side and server-side validation
- **Modern UI**: Clean, professional design with animations

## Tech Stack

- **Backend**: Node.js with Express.js
- **Frontend**: HTML5, CSS3, JavaScript
- **Form Parsing**: body-parser middleware
- **Styling**: Custom CSS with Google Fonts

## Installation & Setup

1. **Navigate to the project directory:**
   ```bash
   cd lesson_21/express_server_jaydenellis
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start the server:**
   ```bash
   npm start
   ```
   
   Or for development:
   ```bash
   npm run dev
   ```

4. **Open your browser and visit:**
   - Homepage: http://localhost:3000
   - Signup Form: http://localhost:3000/signup
   - Dashboard: http://localhost:3000/dashboard

## Port Forwarding (Dev Container)

If you're using a dev container, you may need to forward port 3000 to access the server from your host machine:

1. In VS Code, press `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)
2. Type "Ports: Focus on Ports View" and select it
3. Click "Forward a Port" and enter `3000`
4. The server will be accessible at the forwarded URL

## Routes

- `GET /` - Homepage with Code Differently information
- `GET /signup` - Signup form page
- `POST /signup` - Process signup form submission
- `GET /dashboard` - View all signups

## Form Fields

The signup form includes:
- **First Name** (required)
- **Last Name** (required)
- **Email Address** (required)
- **Phone Number** (optional, auto-formatted)
- **Password** (required, minimum 8 characters)
- **Program Interest** (dropdown selection)
- **Terms Agreement** (required checkbox)
- **Newsletter Subscription** (optional checkbox)

## Features

### Client-Side Validation
- Real-time password strength validation
- Phone number auto-formatting
- Required field validation
- Form submission loading state

### Server-Side Validation
- Required field checking
- Duplicate email prevention
- Error handling with user-friendly messages

### Data Management
- In-memory storage (for demo purposes)
- User signup tracking
- Dashboard for viewing all signups

## File Structure

```
express_server_jaydenellis/
├── server.js          # Express server and routes
├── index.html         # Homepage
├── signup.html        # Signup form page
├── style.css          # All CSS styles
├── package.json       # Dependencies and scripts
├── README.md          # This file
├── logo.png           # Code Differently logo
└── hero.jpg           # Hero section background image
```

## Customization

- Modify `server.js` to add new routes or change server logic
- Update `style.css` to change the appearance
- Edit HTML files to modify content and form fields
- Add database integration by replacing the in-memory `signups` array

## Production Considerations

For production deployment, consider:
- Using a real database (MongoDB, PostgreSQL, etc.)
- Adding password hashing (bcrypt)
- Implementing proper session management
- Adding CSRF protection
- Setting up environment variables
- Adding input sanitization
- Implementing rate limiting

## Author

Jayden Ellis - Code Society 25-2
