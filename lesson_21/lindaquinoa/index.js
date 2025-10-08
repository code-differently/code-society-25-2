const express = require('express');
const morgan = require('morgan');
const path = require('path');
var debug = require('debug')('myapp:server');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(morgan('dev'));
app.use(express.static('public'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Routes
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/contact', (req, res) => {
    const { name, email, message } = req.body;
    // Respond with a simple confirmation page showing submitted data
    res.send(`
        <h1>Thank you for contacting us!</h1>
        <p><strong>Name:</strong> ${name}</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Message:</strong> ${message}</p>
        <a href="/">Back to Home</a>
    `);
});

// Start server
app.listen(PORT, () => {
    debug(`Server listening on http://localhost:${PORT}`);
});