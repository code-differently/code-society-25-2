const express = require("express")

const app = express()
app.use(express.static("public"))
app.set('view engine', 'ejs')
app.get('/', (req, res) => {
    console.log("Here")
    res.render('index')
  
})

app.get('/contact',(req, res) => {
    console.log("Hi")
    res.render('contact')
}).post('/contact', (req, res)=>{
    res.send('Hello')
})


app.listen(3000);