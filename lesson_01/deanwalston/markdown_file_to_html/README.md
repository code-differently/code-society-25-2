# Markdown to HTML Converter

## 📜 Overview
This Python script converts a Markdown (`.md`) file into a fully formatted HTML (`.html`) file.  
It uses the [`markdown`](https://pypi.org/project/Markdown/) library to parse and convert Markdown syntax into HTML and wraps it in a basic HTML template.  

This is useful for:
- Quickly previewing Markdown documents in a browser.
- Creating static HTML pages from Markdown notes or documentation.
- Converting `.md` files without manually copying and pasting into online tools.

---

## 🛠 Features
- Reads a Markdown file and converts it to HTML.
- Wraps the HTML in a basic structure (`<html>`, `<head>`, `<body>`).
- Links to an optional external CSS file (`style.css`) for custom styling.
- Handles file-not-found and unexpected errors gracefully.

---

## 📦 Installation


1. **Install Python (if not already installed)**  
   - [Download Python](    brew install python  ) (version 3+ recommended). 
    - Verify by.    python3 --version
 

2. **(Optional)Create and Activate a Virtual Environment**  
   Using a virtual environment ensures dependencies are isolated from your global Python installation.

   **On macOS:**
   ```bash
   python3 -m venv venv
   source venv/bin/activate
   ```
3. **Install dependencies**  
   The script uses the `markdown` and `os`library. Install it via:
   ```
   python -m pip install markdown

   import os
   ```

---

## ▶️ Usage

1. Place your Markdown file (e.g., `my_doc.md`) in the same directory as the script or note its full path.

2. Run the script:
   ```
   python convert_markdown_file_to_html.py
   ```

3. When prompted, enter the path to your Markdown file:
   ```
   Enter the path to the Markdown file: my_doc.md
   ```

4. The converted HTML file will then be saved in the same location as your Markdown file, with the `.html` extension.

---

## Tech Used

- HTML, CSS
- Pyhton
- GitHub Markdown CSS

## ⚠️ Error Handling
- If the file path is incorrect, the script will display:
  ```
  Error: File not found at 'your_file.md'
  ```
- Any other unexpected errors will be printed to the console.
