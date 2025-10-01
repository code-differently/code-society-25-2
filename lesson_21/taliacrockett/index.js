const express = require('express') 
const app = express()
const port = 4001
const cors = require('cors')
const path = require('path')

app.use(express.urlencoded({ extended: true }))
app.use(express.json())
app.use(cors())

// Serve static files (CSS, images, etc.)
app.use(express.static(__dirname))

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'))
})

app.post("/contact", (req, res) => {
    const formData = req.body
    console.log('Form Data Received:', formData)
    res.json({ message: 'Form submission received successfully!' })
})


app.listen(port, () => {
    console.log(`Listening at http://localhost:${port}`)
});
