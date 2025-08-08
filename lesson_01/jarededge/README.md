# Lesson_01 HW - Markdown to HTML Converter

## Overview

When tasked with this assignment the first thing I thought about was having the user give an input, run a program, and it would return the desired output. I originally thought about making the program in java using a scanner that takes an input of string chars and converts it into html.

Although technically it could work, it would be very difficult to do. For example, say the user input had “>”. When running through the program I would have to explicitly find a work around for distincting a string “>” and an html tag “>”.

So I began to think about another way to go about it and realized I could use a tool that I’ve used before online, a converter. There have been plenty of times where I needed to convert an image from jpg to png, jpeg, etc. Which takes an input, runs a program, and returns the desired result. The same could work with this task. I could create a simple html page that takes an input (markdown language) , runs a program, and gives the output (html language). So this is what I’ve created and I'm going to explain how it works.

---

## File Structure

### `index.html`

The `index.html` file is tasked with giving the page structure and loading dependencies. I found a dependency online that parses markdown. This does most of the work for the conversion. The rest of the file is just front-end styling with a button click that runs `convertMarkdown()` from my js file.

---

### `style.css`

The `style.css` file is solely for matching the style of github preview.

---

### `main.js`

The `main.js` file does the rest of what the parser can do on its own. For example,


html: true,   // allows for embedded html in markdown so the user can preview the conversion  
breaks: true, // and line breaks in markdown will be converted to <br> tags  
linkify: true // any links found in the markdown will be converted to <a> tags  

The second half of the js file reads the input from the <textarea> which is then applied to the parser using md.render(). Then the output is returned. Once returned the css styling is applied to make the html look like the 1 to 1 copy.


## Conclusion

The only issue I am still fighting is how to get images to properly appear as they do in markdown. I believe more css style to deal with photos is needed but I didn’t have enough time to complete that specific task before the due date but am still currently working on it.

