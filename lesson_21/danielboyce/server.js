const express = require('express');
const app = express();
const PORT = 3000;


app.use(express.static("public"))
app.get('/new', (req, res) => {
  res.render()
});


app.listen(PORT, () => {})