const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static('public'));

// Set EJS as template engine (optional)
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Routes

// Home page route
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Contact page route
app.get('/contact', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'contact.html'));
});

// Handle contact form submission (POST)
app.post('/contact', (req, res) => {
    const { name, email, subject, message } = req.body;
    
    // Process the form data (for now, just log it)
    console.log('Contact form submission:', {
        name,
        email, 
        subject,
        message,
        timestamp: new Date().toISOString()
    });

    // Send response back to user
    res.send(`
        <html>
        <head>
            <title>Thank You</title>
            <link rel="stylesheet" href="/style.css">
        </head>
        <body>
            <div class="container">
                <h1>Thank You!</h1>
                <p>Hi ${name}, thank you for your message!</p>
                <p><strong>Subject:</strong> ${subject}</p>
                <p><strong>Message:</strong> ${message}</p>
                <p>We'll get back to you at ${email} soon.</p>
                <a href="/">← Back to Home</a>
                <br>
                <a href="/contact">← Send Another Message</a>
            </div>
        </body>
        </html>
    `);
});

// 404 handler
app.use((req, res) => {
    res.status(404).send('<h1>404 - Page Not Found</h1><a href="/">Go Home</a>');
});

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
    console.log('Press Ctrl+C to stop the server');
});

module.exports = app;