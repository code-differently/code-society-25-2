# Markdown to HTML converver

# Description
This python script will open the desired README file and convert it to an HTML file. It has a bash file that can be used to activate it.

# Used Files
The files that are used goes as followed
- conversion.py
- MarkdownCSS.css
- convert.sh
- test.html
- README.md

# How to use
To be able to run this the intended way you must make the bash file executable. To do that you must run the command **chmod +x convert.sh**. Afterwards you have to run the bash script. This can be done by using the command **./convert.sh README.md** which will create a file called test.html with the converted README. This can be done with any markdown file, all you have to do is change the name of the file within the command. (README.md is my example)

# Dependencies
This code uses no outside libraries so there is no need to download anything. The only library that is being imported is the system library (ie import sys)

# Notes
This is a very rudimentary python script as it only does the most basic of basic tasks, so do not expect a perfect one to one creation. That is because I only accounted for the creation of headers and body tags being used throughout the document. So no lists or emboldened words. It also does not follow basic indentation for an html file.