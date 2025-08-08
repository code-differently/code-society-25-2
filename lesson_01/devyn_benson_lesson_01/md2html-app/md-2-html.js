const fs = require('fs');
const path = require('path');
const { marked } = require('marked');

const markdown = fs.readFileSync('README.md', 'utf8'); // read input
const html = marked(markdown);        
const page = `<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <!-- GitHub Markdown CSS (CDN) -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown.min.css">
  <style>
    /* Lightweight layout to mimic GitHub’s content width/padding */
    body {
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif;
      background: #fff;
      line-height: 1.5;
      padding: 24px;
    }
    .markdown-body {
      box-sizing: border-box;
      max-width: 980px;
      margin: 0 auto;
      padding: 24px;
    }
    @media (max-width: 767px) { .markdown-body { padding: 16px; } }
  </style>

  <!-- (Optional) code block highlighting like GitHub -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/github.min.css">
</head>
<body>
  <article class="markdown-body">
    ${html}
  </article>

  <!-- (Optional) highlight.js init -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
  <script>hljs.highlightAll();</script>
</body>
</html>`;                  // convert

const outPath = path.join(__dirname, 'output.html'); //write wrapped HTML with styles from GitHub CSS
fs.writeFileSync(outPath, page, 'utf8');
console.log('✅ Wrote:', outPath, 'bytes:', page.length);
// Output: HTML file created from README.md
// Note: Ensure that 'marked' is installed via npm before running this script.
// Command to run: node md-2-html.js
// Usage: Place this script in the same directory as README.md and run it.