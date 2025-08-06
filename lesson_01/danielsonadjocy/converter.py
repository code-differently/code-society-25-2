import markdown
import sys
from pathlib import Path

def convert_md_to_html(markdown_file, html_file):
    pass
if __name__ == "__main__":
    try:
        readme_md = sys.argv[1]
        readme_html = sys.argv[2] if len(sys.argv) > 2 else "README.html"
        print(readme_html)
        convert_md_to_html(readme_md, readme_html)
    except Exception as e:
        print("Please give the name of the markdown file.")
