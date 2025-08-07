const md = window.markdownit({
  html: true,
  breaks: true,
  linkify: true
});

function convertMarkdown() {
  const markdown = document.getElementById("markdownInput").value;
  const html = md.render(markdown);
  document.getElementById("htmlOutput").innerHTML = html;
}