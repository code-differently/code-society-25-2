const express = require("express")

const app = express()
var debug = require('debug')('myapp:server');

app.use(express.static("public"))
app.set('view engine', 'ejs')
app.use(express.urlencoded({extended:true}));

app.get('/', (req, res) => {
    res.render('index')
  
})

app.get('/contact',(req, res) => {
    res.render('contact')
}).post('/contact', (req, res)=>{
    console.log(req.body.firstname)
    res.render('displayInfo',req.body)
})


const PORT = process.env.PORT || 3000; 
 
app.listen(PORT, () => {
 debug(`Server listening on http://localhost:${PORT}`);
});