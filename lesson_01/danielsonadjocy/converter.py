import markdown
import sys
import re

def convert_md_to_html(markdown_file, html_file):
    f = open(markdown_file, "r", encoding="utf-8")
    md = f.read()

    write = open(html_file, "w", encoding="utf-8")


    md = re.sub(r"([^\n])\n([*+-] )", r'\1\n\n\2', md)
    md = re.sub(
        r'(?<!\()(?<!\]\()(?<!href=")(https?://[^\s<]+)',
        r'[\1](\1)',
        md
    )
    
    html = markdown.markdown(md, extensions=['fenced_code', 'codehilite'])

    full_html = f"""<!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <title>{markdown_file[:-3]} html</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/github-markdown-css@5/github-markdown.min.css">    
    </head>
    <body class="markdown-body">
    {html}
    </body>
    </html>
    """

    write.write(full_html)
    f.close()
    write.close()

if __name__ == "__main__":
    try:
        readme_md = sys.argv[1]
        readme_html = sys.argv[2] if len(sys.argv) > 2 else readme_md[:-3]+ ".html"
        convert_md_to_html(readme_md, readme_html)
        print(readme_html, "created")
    except Exception as e:
        print("Please give the name of the markdown file.")