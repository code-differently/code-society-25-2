# 📄 Markdown to GitHub-Styled HTML Converter

This project takes your **Markdown** file (like `README.md`) and turns it into a **Webpage** that looks just like GitHub’s style.  
You write in simple Markdown, run a Node.js script, and get a clean HTML page.

---

## 🧠 How It Works (Step-by-Step)

### 1. Start with a Markdown file
Markdown is plain text with some symbols for formatting:
```md
# My Title
- Point 1
- Point 2
```
It’s easy to write but doesn’t look styled in a browser.

---

### 2. Read the file
The script uses Node.js’s `fs` module to read the Markdown file:
```js
const markdown = fs.readFileSync('README.md', 'utf8');
```

---

### 3. Convert Markdown → HTML
We use the [`marked`](https://www.npmjs.com/package/marked) library to turn Markdown into HTML:
```js
const html = marked(markdown);
```
Now we have:
```html
<h1>My Title</h1>
<ul>
  <li>Point 1</li>
  <li>Point 2</li>
</ul>
```
…but it’s just plain HTML without GitHub’s style.

---

### 4. Wrap HTML in a complete page
We create a **page template** that includes:
- `<!doctype html>` and `<html>` / `<head>` tags
- A `<link>` to **GitHub’s Markdown CSS** (via CDN)
- A `<article class="markdown-body">` wrapper — required for the CSS to work
- Small custom CSS for width and padding
- *(Optional)* highlight.js CSS & JS for code block colors

Example wrapper:
\`\`\`html
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <!-- GitHub Markdown CSS -->
  <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown.min.css">
  <style>
    body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif; background: #fff; line-height: 1.5; padding: 24px; }
    .markdown-body { max-width: 980px; margin: 0 auto; padding: 24px; }
    @media (max-width: 767px) { .markdown-body { padding: 16px; } }
  </style>
</head>
<body>
  <article class="markdown-body">
    \${html}
  </article>
</body>
</html>
\`\`\`

---

### 5. Write to `output.html`
We save the wrapped HTML page:
```js
fs.writeFileSync('output.html', page, 'utf8');
```
Open it in your browser to see the styled result.

---

## 🚀 How to Use

1. **Install Node.js** (if you don’t have it yet) → [https://nodejs.org](https://nodejs.org)

2. **Install `marked`**:
\`\`\`bash
npm install marked
\`\`\`
*(Optional for code highlighting)*:
\`\`\`bash
npm install highlight.js
\`\`\`

3. **Put your Markdown file in the same folder** as `md-2-html.js`.

4. **Run the script**:
\`\`\`bash
node md-2-html.js
\`\`\`

5. **Open `output.html`** in your browser.

---

## 🎨 Features

- ✅ **GitHub style** — Fonts, headings, lists, tables, blockquotes, and code blocks match GitHub.
- ✅ **Any Markdown file** — README, notes, documentation.
- ✅ **Optional syntax highlighting** for code blocks.
- ✅ **Offline or online** — Use a local CSS file or the CDN link.
- ✅ **Mobile-friendly** with responsive padding.

---

## 📂 Example Folder Structure
\`\`\`
my-project/
├── README.md        # Your Markdown file (input)
├── md-2-html.js     # The Node.js script
├── package.json
└── output.html      # The generated HTML (output)
\`\`\`

---

## 🕵️‍♂️ Tips & Troubleshooting

- If it looks plain: check that your HTML has `<article class="markdown-body">`.
- If styles aren’t loading: check your internet connection or CSS file path.
- If code blocks aren’t colored: add highlight.js and run the script again.

---

## 📊 Process Flow

\`\`\`
Markdown file (.md)
      ↓
marked (Markdown → HTML)
      ↓
Wrap in full HTML page + GitHub CSS
      ↓
output.html (styled webpage)
\`\`\`

---

## 💡 Key Learnings

- GitHub’s Markdown CSS only works inside `.markdown-body`.
- Wrapping your HTML in a proper document ensures correct rendering.
- CDN is fastest for testing, but local CSS works offline.
- Optional syntax highlighting makes code look better.