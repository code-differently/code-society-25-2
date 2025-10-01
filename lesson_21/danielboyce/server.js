const express = require("express");
const session = require("express-session");
const app = express();
const PORT = 3000;

app.set("view engine", "ejs");
app.use(express.static("public"));
app.use(express.urlencoded({extended:true}));
app.use(session({
  secret:"your_secret_key",
  resave:false,
  saveUninitialized:true
}))
app.get("/new", (req, res) => {
  res.render("user-input");
});

app.post("/new", (req, res) => {
  const user = req.body;
  // console.log({
  //   "First Name":user.firstName,
  //   "Last Name":user.lastName,
  //   "Email":user.email,
  //   "Phone Number":user.phone

  // })
  req.session.user = user;
  res.redirect("/info");
});

app.get("/info",(req,res)=> {
  const data = req.session.user;
  if(!data) {
    return res.redirect("/new");
  }
  
  res.render('user-view', data);
})


app.listen(PORT, () => {});
