import markdown


def main():
    try:
        with open("../README.md", "r", encoding="utf-8") as md_file:
            markdown_content = md_file.read()

        html_content = markdown.markdown(markdown_content)
        print("\nConverted HTML content")

        with open("./htmlmarkdown", "w") as f:
            f.write("<!DOCTYPE html>\n")
            f.write("<html lang='en'>\n")
            f.write("""<head>
                <meta charset = "UTF-8" >
                <link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown-light.min.css" >
                <style >
                body {
                    max-width: 800px;
                    margin: auto;
                    padding: 2rem;
                }
                </style >
            </head >
            <body class = "markdown-body" >""")
            f.write(html_content + "\n")
            f.write("</html>")

    except FileNotFoundError:
        print("Error: The specified Markdown file was not found.")
    except Exception as e:
        print(f"An error occurred during Markdown processing: {e}")


if __name__ == "__main__":
    main()
