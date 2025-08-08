import argparse
import markdown

# This function converts Markdown code into HTML code


def convert_md_to_html(input_file, output_file):
    with open(input_file, 'r', encoding="utf-8") as f:
        # reads the content of the markdown file
        markdown_content = f.read()

        # converts the markdown content to html
        html = markdown.markdown(markdown_content)

        # writes the html code to a file
        with open(output_file, 'w', encoding="utf-8") as f:
            f.write(html)

            print(f"html file created: {output_file}")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Convert Markdown to HTML")
    parser.add_argument("input", help="Input Markdown file")
    parser.add_argument("output", help="Output HTML file")

    args = parser.parse_args()
    convert_md_to_html(args.input, args.output)
