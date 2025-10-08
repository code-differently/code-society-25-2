const express = require('express');
const app = express();
const port = 3000;
const cors = require('cors');

app.use(express.urlencoded({ extended: true }))
app.use(express.json());
app.use(cors()); // Enable CORS for all routes
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
});
app.post('/contact', (req, res) => {
  console.log(req.body);
  res.json({ success: true });
});

app.get('/', (req, res) => {
    res.send('This is working');
});


app.listen(port, () => {
  console.log(`Listening at http://localhost:${port}`)

})