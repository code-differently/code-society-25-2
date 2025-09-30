const express = require("express"); // Get express
const morgan = require("morgan"); // Get morgan
const path = require("path"); // Get path
var debug = require('debug')('myapp:server'); // Get debug logger
 
const app = express(); // Create express app
 
app.use(morgan("dev")); // Setup morgan middleware
app.use(express.static(path.join(__dirname, "public"))); // Setup static files
app.use(express.urlencoded({ extended: true })); // Parse URL-encoded bodies
app.use(express.json()); // Parse JSON bodies
 
// Handle GET request to signup (redirect to signup folder)
app.get('/signup', (req, res) => {
    res.redirect('/signup/');
});

// Handle sign up form submission
app.post('/signup', (req, res) => {
    debug('Sign up form submission received:', req.body);
    
    // Extract form data
    const { firstName, lastName, email, phone, program, experience, goals, agreeToTerms } = req.body;
    
    // Create URL parameters with the form data
    const params = new URLSearchParams({
        firstName: firstName || '',
        lastName: lastName || '',
        email: email || '',
        phone: phone || '',
        program: program || '',
        experience: experience || '',
        goals: goals || '',
        agreeToTerms: agreeToTerms || ''
    });
    
    // Redirect to the thank you page with the data as parameters
    res.redirect(`/signup/thankyou.html?${params.toString()}`);
});

const PORT = process.env.PORT || 3000; // Setup port
 
// Start the server
app.listen(PORT, () => {
 debug(`Server listening on http://localhost:${PORT}`);
});
