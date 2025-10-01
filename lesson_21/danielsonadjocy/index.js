const express = require("express"); // Get express
const morgan = require("morgan"); // Get morgan
const path = require("path"); // Get path
var debug = require('debug')('myapp:server'); // Get debug logger
 
const app = express(); // Create express app
 
app.use(morgan("dev")); // Setup morgan middleware
app.use(express.static(path.join(__dirname, "public"))); // Setup static files

// Add middleware to parse form data
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Handle signup POST request
app.post('/signup', (req, res) => {
    const { firstName, lastName, email, PhoneNumber, password, confirmPassword } = req.body;

    console.log('Signup data:', {
        firstName,
        lastName,
        email,
        PhoneNumber,
        passwordProvided: !!password
    });
    
    // Basic validation
    if (password !== confirmPassword) {
        return res.redirect('/SignUp/signup.html?error=passwords_mismatch');
        //return res.status(400).send('Passwords do not match');
    }
    
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <title>Signup Successful - Code Differently</title>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="/success.css">
        </head>
        <body>
            <div class="success-container">
                <div class="success-content">
                    <h1>🎉 Signup Successful!</h1>
                    <p>Welcome, ${firstName}! Thank you for joining Code Differently! We're excited to have you on board.</p>
                    <p>You'll receive a confirmation email shortly with next steps.</p>
                    <a href="/" class="back-button">Back to Home</a>
                </div>
            </div>
        </body>
        </html>
    `);
});
 
const PORT = process.env.PORT || 3000; // Setup port
 
// Start the server
app.listen(PORT, () => {
 debug(`Server listening on http://localhost:${PORT}`);
});