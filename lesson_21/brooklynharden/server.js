const express = require("express")

const app = express()
app.use(express.static("public"))
app.set('view engine', 'ejs')
app.use(express.urlencoded({extended:true}));
app.get('/', (req, res) => {
    console.log("Here")
    res.render('index')
  
})

app.get('/contact',(req, res) => {
    console.log("Hi")
    res.render('contact')
}).post('/contact', (req, res)=>{
    const user = req.body;
    console.log({
        "First Name:": user.firstName,
        "Last Name:" : user.lastName,
        "Email Address:": user.email,
        "Phone Number:":user.phone
    })
    req.sesson.user = user;
    res.redirect("/info");
})


app.listen(3000);