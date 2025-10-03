const express = require('express');
    const app = express();
    const PORT = 3000;

    // Middleware to parse form data
    app.use(express.urlencoded({ extended: true }));
    app.use(express.json());

    // Serve static files from public folder
    app.use(express.static('public'));

    // Handle contact form submission
    app.post('/submit-contact', (req, res) => {
        const { name, email, message } = req.body;
        
        res.send(`
            <h1>Thank You!</h1>
            <p><strong>Name:</strong> ${name}</p>
            <p><strong>Email:</strong> ${email}</p>
            <p><strong>Message:</strong> ${message}</p>
            <br>
            <a href="/contact.html">Go Back</a>
        `);
    });

    // Start the server
    app.listen(PORT, () => {
        console.log(`Server running on http://localhost:${PORT}`);
    });