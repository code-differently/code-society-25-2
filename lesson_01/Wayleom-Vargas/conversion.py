import sys

#Bash script version

def html_converter(md_filename):
    html_filename = "test.html"  # output file fixed

    with open(md_filename, 'r') as readme, open(html_filename, 'w') as test:
        test.write("<!DOCTYPE html>\n<html>\n<body>\n")
        test.write('<head>\n<link rel="stylesheet" href="MarkdownCSS.css">\n</head>\n')

        for line in readme:
            line = line.rstrip('\n')
            if line.strip() == "":
                continue

            count = 0
            for char in line:
                if char == '#' and count < 6:
                    count += 1
                else:
                    break

            content = line[count:].strip()

            if count > 0:
                test.write(f"<h{count}>{content}</h{count}>\n")
            else:
                test.write(f"<p>{content}</p>\n")

        test.write("</body>\n</html>")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python3 conversion.py <markdown_file>")
        sys.exit(1)
    html_converter(sys.argv[1])
    print(f"Converted {sys.argv[1]} to test.html")


# def ContentPrinter():
#     f = open("README.md","r")
#     print(f.read())

# ContentPrinter()

# def CreateAFIle():
#     f = open("test.txt", "x")
#     f.write("This is a test")
# CreateAFIle()

# def ContentCopy():
#     with open('README.md','r') as README, open('test.html','a') as test:
    
#     # read content from first file
#         for line in README:
             
#              # append content to second file
#              test.write(line)

# ContentCopy()

# def html_converter():
#     with open('README.md','r') as README, open('test.html','a') as test:
#         test.write("<!DOCTYPE html> \n")
#         for line in README:
#             str(line)
#             stripped = line.strip()

#             if '#' in line[0]:
#                 newline = line.rstrip('\n')
#                 count = len(stripped) - len(stripped.lstrip('#'))
#                 content = stripped[count:].strip()  # Remove the # symbols and extra space
#                 test.write("<h1>")
#                 test.write(f"<h{count}>{newline}</h{count}>\n") 
#                 test.write("</h1>")
#             test.write(line)

# html_converter()

#None Bash Script Version
# def html_converter(md_filename):
#     html_filename = "test.html"

#     with open(md_filename, 'r') as readme, open(html_filename, 'w') as test:
#         test.write("<!DOCTYPE html>\n<html>\n<body>\n")
#         test.write('<head>\n<link rel="stylesheet" href="MarkdownCSS.css">\n</head>\n')

#         for line in readme:
#             line = line.rstrip('\n')
#             if line.strip() == "":
#                 continue

#             count = 0
#             for char in line:
#                 if char == '#' and count < 6:
#                     count += 1
#                 else:
#                     break

#             content = line[count:].strip()

#             if count > 0:
#                 test.write(f"<h{count}>{content}</h{count}>\n")
#             else:
#                 test.write(f"<p>{content}</p>\n")

#         test.write("</body>\n</html>")

# html_converter("README.md")


