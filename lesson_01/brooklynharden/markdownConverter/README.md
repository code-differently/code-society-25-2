# 📝 Markdown To HTML Script

This program turns any Markdown file into a complete HTML document using Node.js.

**Created By:** Brooklyn Harden

---

## 📖 How It Works

1. **Start with a Markdown file**  
   Markdown is plain text with some symbols for formatting.

2. **Read the file**  
   The script uses Node.js `fs` module to read the Markdown file:

   ```js
   const markdownContent = fs.readFileSync(markdownPath, "utf-8");
Convert Markdown → HTML
The script uses the marked library to convert Markdown to HTML:
```js
const htmlContent = marked(markdownContent);
```
Wrap HTML document in a complete page
The converted Markdown is wrapped in HTML tags:

A <link> to GitHub's Markdown CSS stylesheet

Small custom CSS for width, margin, and padding

Create output folder and write HTML file
The HTML file is saved as converted.html inside the output folder:

 ```js
fs.writeFileSync("output/converted.html", fullHtml);
```
See the HTML output
After running the script, you can open converted.html in your browser to view your Markdown content styled as a full HTML page.

🚀 How to Use
Install Node.js (if not already installed)
Download it from https://nodejs.org

Install dependencies:

```js
npm install marked
```
Navigate to the folder
Make sure you are in the folder where app.js and your Markdown file are located.

Run the script:

```js
node app.js READMEtoHTMLconverter.md
```
Check the output
The converted HTML file will be saved in the output folder as converted.html. Open it in your browser to view the results.