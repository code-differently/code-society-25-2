const express = require('express');
const app = express();
const PORT = 3000;

app.use(express.static('public'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.post('/contact', (req, res) => {
    const {name, email, message} = req.body;

    console.log('Contact form submission:');
    console.log(`Name: ${name}`);
    console.log(`Email: ${email}`);
    console.log(`Message: ${message}`);

    res.send('Thank you for your message! We will get back to you soon.');
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
