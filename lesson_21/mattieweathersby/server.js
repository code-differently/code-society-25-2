//import the express module to handle routing
const express = require('express');
const app = express();
const path = require('path');

const PORT = 3000;



// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Middleware to parse URL-encoded bodies (as sent by HTML forms)
app.use(express.urlencoded({ extended: true }));

// Handle form submission from contact.html
app.post('/contact', (req, res) => {
  console.log(req.body); // see the submitted data in terminal
  res.send(`Thanks, ${req.body.name}! We received your message: ${req.body.message}`);
});

app.listen(PORT, '0.0.0.0', () => {
    console.log(`Express server is running on port ${PORT}`);
});