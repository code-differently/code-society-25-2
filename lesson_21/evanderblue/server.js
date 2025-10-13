const express = require('express');
const path = require('path');

// Create Express application
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware for parsing form data
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Serve static files (CSS, images, etc.) from the public directory
app.use(express.static(path.join(__dirname, 'public')));

// Home page route
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Contact page route - serves the contact form
app.get('/contact', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'contact.html'));
});

// Handle POST request from contact form
app.post('/contact', (req, res) => {
    const { name, email, message } = req.body;
    
    // Here you could save to a database, send an email, etc.
    console.log('Contact form submission:', { name, email, message });
    
    // Send response with the submitted data
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Form Submission Success</title>
            <link rel="stylesheet" href="/style.css">
            <style>
                .success-container {
                    max-width: 600px;
                    margin: 50px auto;
                    padding: 30px;
                    background: #f8f9fa;
                    border-radius: 10px;
                    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                }
                .success-message {
                    color: #28a745;
                    font-size: 1.5em;
                    margin-bottom: 20px;
                }
                .submitted-data {
                    background: white;
                    padding: 20px;
                    border-radius: 5px;
                    border-left: 4px solid #007bff;
                }
                .back-link {
                    display: inline-block;
                    margin-top: 20px;
                    padding: 10px 20px;
                    background: #007bff;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;
                    transition: background 0.3s;
                }
                .back-link:hover {
                    background: #0056b3;
                }
            </style>
        </head>
        <body>
            <div class="success-container">
                <h1 class="success-message">✅ Message Sent Successfully!</h1>
                <div class="submitted-data">
                    <h3>Your submitted information:</h3>
                    <p><strong>Name:</strong> ${name || 'Not provided'}</p>
                    <p><strong>Email:</strong> ${email || 'Not provided'}</p>
                    <p><strong>Message:</strong> ${message || 'Not provided'}</p>
                </div>
                <a href="/" class="back-link">← Back to Home</a>
                <a href="/contact" class="back-link">Send Another Message</a>
            </div>
        </body>
        </html>
    `);
});

// Handle 404 errors
app.use((req, res) => {
    res.status(404).send(`
        <h1>404 - Page Not Found</h1>
        <p>The page you're looking for doesn't exist.</p>
        <a href="/">Go back to home</a>
    `);
});

// Start the server
app.listen(PORT, () => {
    console.log(`🚀 Server is running on http://localhost:${PORT}`);
    console.log(`📁 Serving static files from: ${path.join(__dirname, 'public')}`);
    console.log(`🌐 Visit http://localhost:${PORT} to see your website`);
    console.log(`📝 Contact form available at: http://localhost:${PORT}/contact`);
});

module.exports = app;