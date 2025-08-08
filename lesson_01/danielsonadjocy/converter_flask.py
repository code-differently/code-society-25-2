from flask import Flask, request, jsonify, make_response
from flask_cors import CORS
import markdown
import sys
import re
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
    #Writing should not be necessary in this version
    #write.write(full_html)
    #f.close()
    #write.close()



@app.route("/convert", methods = ["POST"])
def convert_file():

    if "markdown" not in request.files:
        return jsonify({"error": "No file or file not a markdown."}), 400

    file = request.files["markdown"]
    filename = file.filename[:-3]
    readme_md = file.read().decode("utf-8")

    try:
        print(filename)
        readme_html = convert_md_to_html(filename, readme_md)
        response = make_response(readme_html)
        ##Might not be necessary
        response.headers["Content-Type"] = "text/html"
        response.headers["Content-Disposition"] = f"attachment; filename={filename}.html"
        #################################################################################
        return response
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    



if __name__ == "__main__":
    p = 5001
    print("Running on port", p)
    app.run(debug=True, port = p)
    