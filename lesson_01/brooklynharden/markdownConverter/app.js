/**
 * Markdown to HTML Converter
 * Converts a Markdown file into a full HTML document.
 *
 * Created By: Brooklyn Harden
 */

const fs = require("fs");
const { marked } = require("marked");
const path = require("path");

// Get the Markdown file path from command line argument
const markdownPath = process.argv[2];

if (!markdownPath) {
    console.log("⚠️ Please provide a Markdown file.");
    console.log("Example: node app.js README.md");
    process.exit(1);
}

try {
    // 1️⃣ Read the Markdown file
    let markdownContent = fs.readFileSync(markdownPath, "utf-8");

    // 2️⃣ Optional: Update image paths (if images are relative)
    // Example: replace 'images/' with './images/'
    // markdownContent = markdownContent.replace(/!\[(.*?)\]\(images\//g, '![$1](./images/');

    // 3️⃣ Convert Markdown to HTML using 'marked'
    const htmlContent = marked(markdownContent);

    // 4️⃣ Wrap HTML in a complete document
    const fullHtml = `
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Converted Markdown</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown-light.min.css">
    <style>
        body { max-width: 800px; margin: auto; padding: 2rem; }
    </style>
</head>
<body class="markdown-body">
    ${htmlContent}
</body>
</html>
`;

 // Create output folder if it doesn't exist
    const outputDir = path.join(__dirname, "output");
    if (!fs.existsSync(outputDir)) {
        fs.mkdirSync(outputDir);
    }

    // Save HTML in the output folder
    const outputPath = path.join(outputDir, "converted.html");
    fs.writeFileSync(outputPath, fullHtml);

    console.log(`✅ Markdown successfully converted to ${outputPath}`);
} catch (error) {
    console.error("❌ Error reading or converting the Markdown file:");
    console.error(error.message);
}
  