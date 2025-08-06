import markdown


def main():
    try:
        with open("../README.md", "r", encoding="utf-8") as md_file:
            markdown_content = md_file.read()

        html_content = markdown.markdown(markdown_content)
        print("\nConverted HTML content:")
        print(html_content)

    except FileNotFoundError:
        print("Error: The specified Markdown file was not found.")
    except Exception as e:
        print(f"An error occurred during Markdown processing: {e}")


if __name__ == "__main__":
    main()
