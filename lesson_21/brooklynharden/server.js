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
    console.log(req.body.firstname)
    res.render('displayInfo',req.body)
})


app.listen(3000);