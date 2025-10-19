const express = require('express');
const path = require('path');
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware to parse form data
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Serve static files (CSS, images, etc.) from the public folder
app.use(express.static(path.join(__dirname, 'public')));

// Home route - serve the main page
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Contact page route
app.get('/contact', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'contact.html'));
});

// Handle contact form submission
app.post('/contact', (req, res) => {
    const { name, email, subject, message } = req.body;
    
    // In a real application, you would save this to a database
    console.log('Contact form submission received:');
    console.log('Name:', name);
    console.log('Email:', email);
    console.log('Subject:', subject);
    console.log('Message:', message);
    
    // Send a response back to the user
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <title>Thank You - Contact Form</title>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        <body>
            <header class="header">
                <div class="header-logo">
                    <a href="/">
                        <img src="logo.png" alt="Code Differently Logo" />
                    </a>
                </div>
                <ul class="header-top-menu">
                    <li><a href="/">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="/contact">Contact</a></li>
                </ul>
            </header>
            <div class="main">
                <div class="content">
                    <section style="padding: 40px; text-align: center;">
                        <h1>Thank You!</h1>
                        <p>Your message has been received successfully.</p>
                        <div style="background: #f5f5f5; padding: 20px; margin: 20px 0; border-radius: 8px; text-align: left; max-width: 600px; margin: 20px auto;">
                            <h3>Message Details:</h3>
                            <p><strong>Name:</strong> ${name}</p>
                            <p><strong>Email:</strong> ${email}</p>
                            <p><strong>Subject:</strong> ${subject}</p>
                            <p><strong>Message:</strong> ${message}</p>
                        </div>
                        <a href="/" style="display: inline-block; background: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px;">Back to Home</a>
                        <a href="/contact" style="display: inline-block; background: #28a745; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px; margin-left: 10px;">Send Another Message</a>
                    </section>
                </div>
            </div>
            <footer class="footer">
                &copy; 2024 Code Differently
            </footer>
        </body>
        </html>
    `);
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
    console.log('Press Ctrl+C to stop the server');
});
