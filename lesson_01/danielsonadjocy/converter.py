import markdown
import sys
from pathlib import Path
import re

def convert_md_to_html(markdown_file, html_file):
    md = Path(markdown_file).read_text(encoding='utf-8')

    html = markdown.markdown(md, extensions=['fenced_code', 'codehilite'])

    full_html = f"""<!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <title>{Path(markdown_file).stem}html</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/github-markdown-css@5/github-markdown.min.css">    
    </head>
    <body class="markdown-body">
    {html}
    </body>
    </html>
    """

    Path(html_file).write_text(full_html, encoding='utf-8')

if __name__ == "__main__":
    try:
        readme_md = sys.argv[1]
        readme_html = sys.argv[2] if len(sys.argv) > 2 else "README.html"
        print(readme_html)
        convert_md_to_html(readme_md, readme_html)
    except Exception as e:
        print("Please give the name of the markdown file.")
