import sys
import markdown2
def convert_to_html(file_name):
    with open(file_name) as f:
        md_file = f.read()
        html_output = markdown2.markdown(md_file)
        output =file_name.rsplit('.', 1)[0] + '.html'
        with open(output , "w") as f:
            f.write(html_output)
        return output

if __name__ == "__main__":
    convert_to_html(sys.argv[1])
