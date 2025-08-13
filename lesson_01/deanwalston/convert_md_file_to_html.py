import markdown
import os

def convert_markdown_file_to_html(input_file_path, output_file_path):
    """
    Converts a Markdown file to an HTML file.

    Args:
        input_file_path (str): The path to the input Markdown file.
        output_file_path (str): The path to the output HTML file.
    """
    try:
        with open(input_file_path, 'r', encoding='utf-8') as md_file:
            markdown_content = md_file.read()
        
        # Convert Markdown to HTML
        html_content = markdown.markdown(markdown_content)

        # Create a basic HTML structure
        html_output = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Markdown to HTML</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    {html_content}
</body>
</html>
"""

        with open(output_file_path, 'w', encoding='utf-8') as html_file:
            html_file.write(html_output)

        print(f"Successfully converted '{input_file_path}' to '{output_file_path}'")

    except FileNotFoundError:
        print(f"Error: File not found at '{input_file_path}'")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    # Define input and output file paths
    input_md_file = input("Enter the path to the Markdown file: ")
    output_html_file = os.path.splitext(input_md_file)[0] + '.html'

    # Call the conversion function
    convert_markdown_file_to_html(input_md_file, output_html_file)
