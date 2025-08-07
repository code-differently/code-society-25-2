import sys
import markdown2
HTML_TEMPLATE = """<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="styles.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>{title}</title>
</head>
<body>
{content}
</body>
</html>
"""
def extract_title(html_content):
    start_tag = "<h1>"
    end_tag = "</h1>"
    start_index = html_content.find(start_tag)
    end_index = html_content.find(end_tag)
    if start_index != -1 and end_index != -1:
        return html_content[start_index + len(start_tag):end_index].strip()
    return "Document"
def convert_to_html(file_name):
    with open(file_name,"r",encoding="utf-8") as f:
        md_text = f.read()
    html_output = markdown2.markdown(md_text, extras=["fenced-code-blocks", "tables", "code-friendly"])
    title = extract_title(html_output)

    full_html = HTML_TEMPLATE.format(title=title, content=html_output)

    output_path = file_name.rsplit('.', 1)[0] + '.html'
    with open(output_path, "w", encoding="utf-8") as f:
        f.write(full_html)

    return output_path

if __name__ == "__main__":
    convert_to_html(sys.argv[1])
