import markdown


def main():
    try:
        print("Welcome to Tyran's Markdown-To-HTML Converter")
        markdown_file = input("Please enter the path of the mark down file: ")

        with open(markdown_file, "r", encoding="utf-8") as md_file:
            markdown_content = md_file.readlines()

        markdown_file = markdown_file.removesuffix("README.md")
        for i in range(len(markdown_content)):
            if (markdown_content[i].find("src=") != -1):
                index = markdown_content[i].find("src=")
                markdown_content[i] = markdown_content[i][:index + 4] + \
                    "\"" + markdown_file + markdown_content[i][index + 5:]

        markdown_content = ''.join(markdown_content)
        html_content = markdown.markdown(markdown_content)

        with open("./htmlmarkdown.html", "w") as f:
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

        print("Converted Markdown To HTML content")

    except FileNotFoundError:
        print("Error: The specified Markdown file was not found.")
    except Exception as e:
        print(f"An error occurred!\nError: {e}")


if __name__ == "__main__":
    main()
