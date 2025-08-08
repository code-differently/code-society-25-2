const fs = require('fs'); // Node's built-in file system module — lets us read/write files. This allows the app to read the Markdown file and write the HTML output.
const MarkdownIt = require('markdown-it'); // Main Markdown-to-HTML converter. This converts your Markdown text into HTML.
const taskLists = require('markdown-it-task-lists'); // Plugin for [ ] task list support. Extra features your Markdown file might use.
const deflist = require('markdown-it-deflist'); // Plugin for definition list support. Extra features your Markdown file might use.
const footnote = require('markdown-it-footnote'); // Plugin for footnotes. Extra features your Markdown file might use.
const mark = require('markdown-it-mark'); // Plugin for ==highlighting== text. Extra features your Markdown file might use.

// Create a MarkdownIt instance and use the extra plugins. This is the "engine" that takes Markdown text and renders it into rich HTML.
const md = new MarkdownIt()
  .use(taskLists)
  .use(deflist)
  .use(footnote)
  .use(mark);

// Grab the input and output file names from command line arguments. Lets you specify which Markdown file to convert and where to save the HTML.
const [,, inputFile, outputFile] = process.argv;

// If either file name is missing, show the user how to run the program. This prevents from crashing if the user forgets to provide the file names.
if (!inputFile || !outputFile) {
  console.error('Usage: node convert.js <input.md> <output.html>');
  process.exit(1); // Stop the program
}

// Read the input Markdown file. This reads the content of the specified Markdown file so we can convert it to HTML.
fs.readFile(inputFile, "utf8", (err, data) => {
  if (err) {
    console.error("Error reading Markdown file:", err);
    return;
  } // If there's an error reading the file, log it and stop the program.

  // Convert Markdown content to HTML
  let htmlContent = md.render(data);

  htmlContent = htmlContent.replace(/<\/h2>/g, '</h2>\n<hr class="section-divider">');
  // This replaces closing <h2> tags with a horizontal rule, adding a visual divider between sections.

  // Wrap the converted content inside a full HTML page structure
  const fullHtml = `
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Markdown to HTML</title>
  <link rel="stylesheet" href="style.css"> <!-- Optional CSS -->
</head>
<body>
${htmlContent}
</body>
</html>
  `;

  // Write the final HTML to the output file. This is how the result is saved.
  fs.writeFile(outputFile, fullHtml, 'utf8', (err) => {
    if (err) {
      console.error('Error writing HTML file:', err);
      return;
    }
    console.log('✅ Markdown converted to HTML successfully!');
  });
});
