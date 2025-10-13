const express = require('express');
const path = require('path');
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(express.urlencoded({ extended: true })); // For parsing form data
app.use(express.json()); // For parsing JSON data
app.use(express.static(path.join(__dirname, 'public'))); // Serve static files from public directory

// GET route - Serve the main page
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public/index.html'));
});

// GET route - Serve the contact form
app.get('/contact', (req, res) => {
    res.send(`
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contact Us - Code Differently</title>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/contact.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    </head>
    <body class="contact-page">
        <header class="header">
            <div class="header-container">
                <div class="header-logo">
                    <a href="/">
                        <img src="/images/logo.svg" alt="Code Differently Logo" />
                    </a>
                </div>
                <nav>
                    <ul class="header-nav">
                        <li><a href="/">Home</a></li>
                        <li><a href="/#programs">Programs</a></li>
                        <li><a href="/contact">Contact</a></li>
                        <li><a href="#about">About</a></li>
                    </ul>
                </nav>
                <div class="header-cta">
                    <a href="/" class="btn">Back to Home</a>
                </div>
            </div>
        </header>
        
        <div class="contact-container">
            <div class="contact-form-wrapper">
                <div class="form-header">
                    <h1 class="form-title">Get In Touch</h1>
                    <p class="form-subtitle">We'd love to hear from you. Send us a message and we'll respond as soon as possible.</p>
                </div>
                
                <form action="/contact" method="POST">
                    <div class="form-group">
                        <label for="name" class="form-label">Full Name</label>
                        <input type="text" id="name" name="name" class="form-input" placeholder="Enter your full name" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" id="email" name="email" class="form-input" placeholder="Enter your email address" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="subject" class="form-label">Subject</label>
                        <input type="text" id="subject" name="subject" class="form-input" placeholder="What's this about?" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="message" class="form-label">Message</label>
                        <textarea id="message" name="message" class="form-textarea" placeholder="Tell us more about your inquiry..." required></textarea>
                    </div>
                    
                    <button type="submit" class="submit-btn">Send Message</button>
                </form>
            </div>
        </div>
    </body>
    </html>
    `);
});

// POST route - Handle form submission
app.post('/contact', (req, res) => {
    const { name, email, subject, message } = req.body;
    
    // Log the form data (in a real app, you'd save to database)
    console.log('Form submission received:');
    console.log('Name:', name);
    console.log('Email:', email);
    console.log('Subject:', subject);
    console.log('Message:', message);
    
    // Send response showing the submitted data
    res.send(`
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thank You - Code Differently</title>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/contact.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    </head>
    <body class="contact-page">
        <header class="header">
            <div class="header-container">
                <div class="header-logo">
                    <a href="/">
                        <img src="/images/logo.svg" alt="Code Differently Logo" />
                    </a>
                </div>
                <nav>
                    <ul class="header-nav">
                        <li><a href="/">Home</a></li>
                        <li><a href="/#programs">Programs</a></li>
                        <li><a href="/contact">Contact</a></li>
                        <li><a href="#about">About</a></li>
                    </ul>
                </nav>
                <div class="header-cta">
                    <a href="/" class="btn">Back to Home</a>
                </div>
            </div>
        </header>
        
        <div class="thank-you-container">
            <div class="thank-you-wrapper">
                <h1 class="thank-you-title">Thank You!</h1>
                <p class="thank-you-message">Your message has been received successfully. We'll get back to you soon!</p>
                
                <div class="submission-details">
                    <h3>Your Submission Details</h3>
                    <div class="detail-item">
                        <span class="detail-label">Name:</span>
                        <div class="detail-value">${name}</div>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Email:</span>
                        <div class="detail-value">${email}</div>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Subject:</span>
                        <div class="detail-value">${subject}</div>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Message:</span>
                        <div class="detail-value">${message}</div>
                    </div>
                </div>
                
                <div class="action-buttons">
                    <a href="/" class="action-btn btn-primary-action">Back to Home</a>
                    <a href="/contact" class="action-btn btn-secondary-action">Send Another Message</a>
                </div>
            </div>
        </div>
    </body>
    </html>
    `);
});

// Test route to verify server is working
app.get('/test', (req, res) => {
    res.send(`
    <h1>Server is Working! ✅</h1>
    <p>The Express server is running correctly.</p>
    <ul>
        <li><a href="/">Visit Homepage</a></li>
        <li><a href="/contact">Visit Contact Form</a></li>
    </ul>
    <p>Current time: ${new Date().toLocaleString()}</p>
    `);
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
    console.log(`Open http://localhost:${PORT} to view the website`);
    console.log(`Open http://localhost:${PORT}/contact to view the contact form`);
    console.log(`Open http://localhost:${PORT}/test to verify server is working`);
});