// Main.java (pseudocode)

/*
Goal: Read a Markdown (.md) file, convert it to HTML, wrap it with GitHub-style
CSS, and write out output.html — using the codedifferently-instructional library.
*/

public class Main {
  public static void main(String[] args) {
    // ── 0) Gather Inputs ───────────────────────────────────────────────────────
    // Input A: path to markdown file (default: "README.md")
    // Input B: output file path (default: "output.html")
    // Input C (optional): use CDN CSS or local CSS; include code highlighting?

    String mdPath     = args.length > 0 ? args[0] : "README.md";
    String outputPath = args.length > 1 ? args[1] : "output.html";
    boolean useCdnCss = true;      // tweak as needed
    boolean highlight = false;     // tweak as needed

    // ── 1) Read .md file ───────────────────────────────────────────────────────
    // read all text from mdPath into a String: markdownText
    // if file not found, print friendly message and exit.

    String markdownText = /* readFile(mdPath) */ null; // TODO: implement

    // ── 2) Convert Markdown → raw HTML ─────────────────────────────────────────
    // Use the codedifferently-instructional library here.
    // ex: MarkdownConverter.convert(markdownText)  (adjust to real class/method)
    String htmlBody = /* CodedDifferentlyMarkdown.convert(markdownText) */ null; // TODO

    // ── 3) Wrap HTML text inside a full HTML document ─────────────────────────
    // Build a template string:
    //  - <!doctype html>, <html>, <head>, <meta charset>, <meta viewport>
    //  - <link> to GitHub Markdown CSS (CDN or local)
    //  - <article class="markdown-body"> {htmlBody} </article>
    //  - (optional) include highlight.js CSS/JS if highlight == true

    String pageHtml = buildPage(htmlBody, useCdnCss, highlight);

    // ── 4) Write page → output file ───────────────────────────────────────────
    // write pageHtml to outputPath (UTF-8). On success, print where it is.

    /* writeFile(outputPath, pageHtml) */ // TODO

    // ── 5) Friendly done message ──────────────────────────────────────────────
    // "Wrote output.html — open it in your browser!"
  }

  // ───────────────────── Helper methods (pseudocode) ──────────────────────────

  // readFile(path): String
  // - open file at 'path'
  // - read all bytes as UTF-8
  // - return text
  // - throw/handle exceptions with a clear message
  static String readFile(String path) { /* ... */ return null; }

  // buildPage(bodyHtml, useCdnCss, highlight): String
  // - choose cssLink = CDN or local path based on useCdnCss
  // - start template with <!doctype html>, <head> (meta + link rel="stylesheet" href=cssLink)
  // - if highlight == true, add highlight.js CSS/JS tags
  // - wrap bodyHtml with <article class="markdown-body">...</article>
  // - return the full HTML page as a String
  static String buildPage(String bodyHtml, boolean useCdnCss, boolean highlight) {
    String cssLink = useCdnCss
        ? "https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown.min.css"
        : "file:./github-markdown.min.css"; // adjust if you keep a local copy
    /* build and return the page string */
    return null;
  }

  // writeFile(path, contents): void
  // - create parent folders if needed
  // - write UTF-8 text to 'path'
  // - handle errors cleanly
  static void writeFile(String path, String contents) { /* ... */ }
}
