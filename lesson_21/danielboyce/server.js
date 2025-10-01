const express = require("express");
const app = express();
const PORT = 3000;

app.set("view engine", "ejs");
app.use(express.static("public"));
app.use(express.urlencoded({extended:true}));

app.get("/new", (req, res) => {
  res.render("user-input");
});

app.post("/new", (req, res) => {
  const user = req.body;
  console.log({
    "First Name":user.firstName,
    "Last Name":user.lastName,
    "Email":user.email,
    "Phone Number":user.phone

  })
  res.redirect("user")
});


app.listen(PORT, () => {});
