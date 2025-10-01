const express = require("express");
const app = express();
const PORT = 3000;

app.set("view engine", "ejs");
app.use(express.static("public"));
app.get("/new", (req, res) => {
  res.render("user-input");
});

app.post("/", (req, res) => {});

app.listen(PORT, () => {});
