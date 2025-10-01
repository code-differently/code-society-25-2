
const express = require('express');
const path = require('path');


const app = express();
const PORT = 4000;


app.use(express.static(path.join(__dirname, 'jarededge','public')));
// parsing for form data 
app.use(express.urlencoded({ extended: true }));
// parsing for JSON bodies 
app.use(express.json());


//(homepage)
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'jarededge','public','index.html'));
});


app.get('/contact', (req, res) => {
  res.sendFile(path.join(__dirname, 'jarededge','public', 'contact.html'));
});


app.post('/contact', (req, res) => {
  const { name, email, message } = req.body;
  res.send(`
    <h1>Thank you, ${name}!</h1>
    <p>We received your message:</p>
    <ul>
      <li><strong>Email:</strong> ${email}</li>
      <li><strong>Message:</strong> ${message}</li>
    </ul>
    <a href="/">Back to Home</a>
  `);
});


// start server
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});