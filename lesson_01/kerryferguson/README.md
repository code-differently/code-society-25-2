# Markdown to HTML Converter

This Python utility transforms Markdown files (such as `README.md`) into fully styled HTML documents. The script reads your Markdown content, preprocesses it to ensure correct list formatting, converts it to HTML using the `markdown` library (with enhanced features and code highlighting), and wraps the result in a complete HTML structure styled with GitHub-flavored CSS.

## Instructions

1. Ensure your Markdown file and the desired output file are located in the same directory as the script.

2. To convert a Markdown file, specify the input and output filenames when running the script:

   ```
   python3 md_converter.py input_file.md output_file.html
   ```

   If the specified file is not found, the script will display an error message. Upon successful conversion, the resulting HTML file will be styled and ready for viewing in any web browser.

3. You may omit the output filename; the script will automatically generate an HTML file with the same base name as your input:

   ```
   python3 md_converter.py input_file.md
   ```

4. If no filenames are provided, the script defaults to converting `README.md` to `README.html`:

   ```
   python3 md_converter.py
   ```

## Image Handling

If your Markdown references images (e.g., `![Alt text](images/pic.jpg)`), ensure the `images` folder is placed in the same directory as your input and output files. This guarantees that your images will be correctly located and displayed when you open the generated HTML file in a browser.