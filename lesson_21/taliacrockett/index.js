const express = require('express') 
const app = express()
const port = 4001
const cors = require('cors')
const path = require('path')

app.use(express.urlencoded({ extended: true }))
app.use(express.json())
app.use(cors())

// Serve static files from public directory
app.use(express.static(path.join(__dirname, 'public')))

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'))
})

app.get("/contact", (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'contact.html'))
})

app.post("/contact", (req, res) => {
    const formData = req.body
    console.log('Form Data Received:', formData)
    res.json({ message: 'Form submission received successfully!' })
})


app.listen(port, () => {
    console.log(`Listening at http://localhost:${port}`)
});
