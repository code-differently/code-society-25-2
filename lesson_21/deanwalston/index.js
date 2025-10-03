
const express = require("express"); // Get express
const morgan = require("morgan"); // Get morgan
const path = require("path"); // Get path
var debug = require('debug')('myapp:server'); // Get debug logger
 
const app = express(); // Create express app
 
app.use(morgan("dev")); // Setup morgan middleware
app.use(express.static(path.join(__dirname, "deanwalston-public-template"))); // Setup static files
app.use(express.urlencoded({ extended: true })); // Middleware to parse URL-encoded bodies
app.use(express.json()); // Middleware to parse JSON bodies
const PORT = process.env.PORT || 3000; // Setup port

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "deanwalston-public-template", "index.html"));
});

app.get("/contact", (req, res) => {
    res.sendFile(path.join(__dirname, "deanwalston-public-template", "contact.html"));
});

// Handle contact form POST
app.post("/contact", (req, res) => {
    const { name, email, message } = req.body;
    
    res.send(`
        <h1>Thank you ${name}!</h1>
        <p>Email: ${email}</p>
        <p>Message: ${message}</p>
        <p>Submitted at: ${new Date()}</p>
        <a href="/contact">Send another message</a> | <a href="/">Home</a>
    `);
});

// Start the server
app.listen(PORT, () => {
 debug(`Server listening on http://localhost:${PORT}`);
 console.log(`Server listening on http://localhost:${PORT}`);
});