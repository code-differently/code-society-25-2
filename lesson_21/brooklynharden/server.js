const express = require("express")

const app = express()
app.use(express.static("public"))
app.set('view engine')
app.get('/', (req, res) => {
    console.log("Here")
    res.render('index')
  
})
app.listen(3000);