import sys
import markdown2
import content
def convert_to_html(file_name):
    with open(file_name) as f:
        md_file = f.read()
        html_output = markdown2.markdown(md_file)
        output =file_name.rsplit('.', 1)[0] + '.html'
        with open(output , "w") as f:
            f.write("<!DOCTYPE html>\n")
            f.write("<html>\n")
            f.write("   <head>\n")
            f.write("       <meta charset='utf-8'>\n")
            f.write("       <meta name='viewport' content='width=device-width, initial-scale=1'>\n")
            start_header = html_output.find("<h1>")
            end_header = html_output.find("</h1>")
            if start_header != -1 and end_header != -1:
                header = html_output[start_header + 4:end_header]
                f.write(f"      <title>{header}</title>\n")        
            
            f.write("   </head>\n")
            f.write("<body>\n")
            body = html_output[end_header + 5:]
            f.write(body) 
            f.write("</body>\n") 
            f.write("</html>\n")
        return output

if __name__ == "__main__":
    convert_to_html(sys.argv[1])
