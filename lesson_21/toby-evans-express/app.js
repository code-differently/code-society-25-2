const express = require('express');
const path = require('path');
const contactRoutes = require('./routes/contact');
const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (_req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.get('/js101', (_req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'js101.html'));
});

app.use(contactRoutes);

app.listen(PORT, () => {});
