var debug = require('debug')('myapp:server'); 
const express = require("express"); 
const morgan = require("morgan"); 
const path = require("path"); 
 
const app = express();

app.use(morgan("dev"));
app.use(express.json()); 
app.use(express.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, "public")));

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "public/sign_up/sign_up.html"));
});

app.post("/sign_up", express.json(), (req, res) => {
    const { name, email, message } = req.body;
    if (!name || !email || !message) {
        return res.status(400).json({ message: "All fields are required." });
    }

    console.log("New sign up:", { name, email, message });
    res.redirect("/sign_up/thank_you.html");
});

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
 debug(`Server listening on http://localhost:${PORT}`);
});