from flask import Flask, request, jsonify, make_response, send_file
from flask_cors import CORS
import markdown, re, zipfile
from io import BytesIO

app = Flask(__name__)
CORS(app)

def convert_md_to_html(filename, md):


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
    <title>{filename} html</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/github-markdown-css@5/github-markdown.min.css">    
    </head>
    <body class="markdown-body">
    {html}
    </body>
    </html>
    """

    return full_html

@app.route("/convert", methods = ["POST"])
def convert_file():
    if "markdown" not in request.files:
        print("No markdown files")
        return jsonify({"error": "No markdown files"}), 400
    
    markdown_files = request.files.getlist("markdown")
    others = request.files.getlist("files")

    # One File
    if len(markdown_files) == 1 and len(others) == 0:
        file = markdown_files[0]
        filename = file.filename[:-3]
        readme_md = file.read().decode("utf-8")
        
        try:
            readme_html = convert_md_to_html(filename, readme_md)
            response = make_response(readme_html)
            response.headers["Content-Type"] = "text/html"
            response.headers["Content-Disposition"] = f"attachment; filename={filename}.html"
            return response
        except Exception as e:
            return jsonify({"error": str(e)}), 500
    #Multiple files
    else:
        zip_buffer = BytesIO()
        with zipfile.ZipFile(zip_buffer, "w", zipfile.ZIP_DEFLATED) as zip_file:
            for md_file in markdown_files:
                filename = md_file.filename[:-3]
                readme_md = md_file.read().decode("utf-8")
                readme_html = convert_md_to_html(filename, readme_md)
                zip_file.writestr(f"{filename}.html", readme_html)
            
            for other_file in others:
                # For images to put them in images folder
                if other_file.filename.lower().endswith(('.png', '.jpg', '.jpeg', '.gif', '.svg')):
                    zip_file.writestr(f"images/{other_file.filename}", other_file.read())
                else:
                    zip_file.writestr(other_file.filename, other_file.read())

        zip_buffer.seek(0)
        return send_file(
            zip_buffer,
            mimetype="application/zip",
            as_attachment=True,
            download_name="converted_files.zip"
        )
    
if __name__ == "__main__":
    p = 5001
    app.run(debug=True, port = p)
    