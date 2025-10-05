const express = require('express');
const path = require('path');
const morgan = require('morgan');
var debug = require('debug')('myapp:server');

const app = express();

app.use(morgan('dev'));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());

const PORT = process.env.PORT || 3000;

app.get('/signup' , (req, res) => {
    res.sendFile(__dirname + '/public/signup/signup.html');
});

app.get('/signupdone' , (req, res) => {
    res.sendFile(__dirname + '/public/signup/signupdone.html');
});

app.post('/signup', (req, res) => {
    const { name, email, password } = req.body;
    debug(`New signup:\n Name: ${name},\n Email: ${email},\n Password: ${password}`);

    res.json({ message: `Thank you for signing up, ${name}!` });
});

app.listen(PORT, () => {
    debug(`Server is running on http://localhost:${PORT}`);
    }
);
