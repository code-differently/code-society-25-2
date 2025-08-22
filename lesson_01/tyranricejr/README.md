# 📝 Markdown To HTML Script

This program turns any Markdown file into a complete HTML document.

#### _Created By: Tyran Rice Jr._

## 📖 How It Works

### 1. Start with a Markdown file
Markdown is plain text with some symbols for formatting

### 2. Read the file
The script uses Pythons `open()` in-built function to read the Markdown file:
```py
with open(markdown_path, "r", encoding="utf-8") as md_file:
```
### 3. Update References
When reading the markdown file, the source functions for images are not pathed properly,
so we must update them:

### 4. Convert Markdown → HTML
We then use the [`markdown`](https://pypi.org/project/Markdown/) library to turn Markdown into HTML:
```py 
html_content = markdown.markdown(markdown_content)
```

### 5. Wrap HTML document in a complete page
We wrap the entire converted markdown file in HTML tags listed below:
- `<!DOCTYPE html>` and `<html>` / `<head>` tags
- A `<link>` to **GitHub's** CSS Markdown Stylesheet
- Small custom CSS for width, margin and padding

### 6. Write to `convertedmarkdown.html`
We save the wrapped HTML page:
```py
with open("./convertedmarkdown.html", "w") as f:
```
We can then see the HTML file in the same folder as `markdownparser.py`.

## 🚀 How to Use

### 1. Install `markdown`:
```bash
pip install Markdown
```
### 2. Navigate To Folder
Starting from the root directory, navigate to
`lesson_01/tyranricejr`. This is where the script is located in.

### 3. Run the script:
```bash
python markdownparser.py
```

### 4. Input your `markdown` file path:
The script will ask you to input your `markdown` file path. This path should be the direct path starting either from the `root` directory or from `lesson_01/tyranricejr` directory.

### 5. Success!
After putting your correct file path, the script
will give you the `HTML` converted version in the output file!