import markdown
import sys
from pathlib import Path
import re


def md_to_html(input_file, output_file):
    # Find and read the markdown file
    md_file = Path(input_file).read_text(encoding='utf-8')
    # Replace single newlines before list items with double newlines for proper formatting
    md_file = re.sub(r'([^\n])\n([*+-] )', r'\1\n\n\2', md_file)
    # Convert the markdown file into HTML
    html = markdown.markdown(md_file, extensions=[
        'extra',   'fenced_code', 'codehilite'])
    # Wrap the HTML in a basic HTML structure with GitHub Markdown CSS
    full_html = f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>{Path(input_file).stem}</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown-light.min.css">
<style>
body{{
        max-width: 800px;
        margin: auto;  
        padding: 2rem;
        }}
</style>
</head>
<body class="markdown-body">
{html}
</body>
</html>
"""
    # Write the HTML to the output file
    Path(output_file).write_text(full_html, encoding='utf-8')
    print(f"Converted '{input_file}' to '{output_file}'")


    # If no arguments are provided, default to "README.md" and "README.html"
    # If file(s) not found, tell user to provide valid file names when running the script
if __name__ == "__main__":
    try:
        input_md = sys.argv[1] if len(sys.argv) > 1 else "README.md"
        output_html = sys.argv[2] if len(sys.argv) > 2 else "README.html"
        md_to_html(input_md, output_html)
    except (Exception):
        print("File(s) not found. Please provide valid file names when running the script.")
