const express = require("express");
const morgan = require("morgan");
const path = require("path");
var debug = require('debug')('myapp:server'); 
 
const app = express();
 
app.use(morgan("dev")); 
app.use(express.static(path.join(__dirname, "public")));

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
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <title>Code Differently</title>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins%3A600%2C400%7CMontserrat%3A800%2C900%2C700&ver=1597678827" type="text/css" media="all">
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