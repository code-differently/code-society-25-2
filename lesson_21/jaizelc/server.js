const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(__dirname));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

app.get('/contact', (req, res) => {
    res.sendFile(path.join(__dirname, 'contact.html'));
});

app.post('/contact', (req, res) => {
    const { name, email, subject, message } = req.body;
    
    console.log('Contact form submission received:');
    console.log('Name:', name);
    console.log('Email:', email);
    console.log('Subject:', subject);
    console.log('Message:', message);
    const responseHTML = `
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Contact Form Submitted - Code Differently</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins%3A600%2C400%7CMontserrat%3A800%2C900%2C700" type="text/css">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header class="header">
            <div class="header-logo">
                <a href="/">
                    <img src="logo.png" alt="Code Differently Logo" />
                </a>
            </div>
            <nav class="header-nav">
                <a href="/">Home</a>
                <a href="/contact">Contact</a>
            </nav>
        </header>
        <main class="main">
            <div class="content">
                <h1>Thank You!</h1>
                <p>Your contact form has been submitted successfully.</p>
                
                <div class="submission-details">
                    <h2>Submission Details:</h2>
                    <p><strong>Name:</strong> ${name}</p>
                    <p><strong>Email:</strong> ${email}</p>
                    <p><strong>Subject:</strong> ${subject}</p>
                    <p><strong>Message:</strong> ${message}</p>
                </div>
                
                <p><a href="/" class="back-link">← Back to Home</a></p>
            </div>
        </main>
        <footer class="footer">
            &copy; 2024 Code Differently
        </footer>
    </body>
    </html>
    `;
    
    res.send(responseHTML);
});

app.use((req, res) => {
    res.status(404).send('<h1>404 - Page Not Found</h1><p>The page you are looking for does not exist.</p>');
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
    console.log(`Access the application at:`);
    console.log(`- Home: http://localhost:${PORT}/`);
    console.log(`- Contact: http://localhost:${PORT}/contact`);
});

module.exports = app;
