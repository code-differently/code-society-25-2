import markdown

'''

Title: Markdown Parser

Description: This program turns your Markdown files into HTML files

Created By: Tyran Rice Jr.

'''

'''

Function Title: read_markdown_file

Description: 
    This function reads the user's markdown file from the
    given path input.

Arguments: 
    - markdown_path: The path to the Markdown file given by the user

Returns:
    - markdown_content: The Markdown file that was read from the given path

'''


def read_markdown_file(markdown_path):
    with open(markdown_path, "r", encoding="utf-8") as md_file:
        markdown_content = md_file.readlines()

    return markdown_content


'''

Function Title: write_html_file

Description: 
    This function takes the user's updated markdown file and converts it
    into a HTML file under the name 'convertedmarkdown.html'.

Arguments: 
    - markdown_content: The updated Markdown file

Returns:
    - void: Simply writes a new HTML file

'''


def write_html_file(markdown_content):
    # Convert Markdown file to HTML
    html_content = markdown.markdown(markdown_content)

    with open("./output/convertedmarkdown.html", "w") as f:
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


'''

Function Title: change_url_path

Description: 
    This function changes the URL path for src references to
    make sure they are correctly pathed to the right directory. 
    This is done by taking the input of the user's path to their 
    existing mark down file and adding it to the src path.

Arguments: 
    - markdown_content: The Markdown file given by the user
    - markdown_path: The path to the Markdown file given by the user

Returns:
    - markdown_content: The new Markdown file with updated src paths

'''


def change_url_path(markdown_content, markdown_path):
    # Removes 'README.md' from path to ensure the path is correctly referenced in src
    markdown_path = markdown_path.removesuffix("README.md")
    for i in range(len(markdown_content)):
        # If "src=" is located in a line, it will be correctly referenced
        if (markdown_content[i].find("src=") != -1):
            index = markdown_content[i].find("src=")
            # Change src to have the correct path location
            markdown_content[i] = markdown_content[i][:index + 4] + \
                "\"" + markdown_path + markdown_content[i][index + 5:]

    return markdown_content


def main():
    try:
        print("Welcome to Tyran's Markdown-To-HTML Converter")
        markdown_path = input("Please enter the path of the mark down file: ")

        # Read the Markdown file and convert it into a list of strings
        markdown_content = read_markdown_file(markdown_path)

        # Update references inside Markdown file
        markdown_content = change_url_path(markdown_content, markdown_path)

        # Join the list of strings from the Markdown file into one entire string
        markdown_content = ''.join(markdown_content)

        # Turn the Markdown file into a HTML file
        write_html_file(markdown_content)

        print("Converted Markdown To HTML content")

    except FileNotFoundError:
        print("Error: The specified Markdown file was not found.")
    except Exception as e:
        print(f"An error occurred!\nError: {e}")


if __name__ == "__main__":
    main()
