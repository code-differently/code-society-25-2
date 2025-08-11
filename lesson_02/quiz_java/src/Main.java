// Main.java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    // 0) Inputs
    String mdPath     = args.length > 0 ? args[0] : "README.md";
    String outputPath = args.length > 1 ? args[1] : "output.html";
    boolean useCdnCss = true;
    boolean highlight = false;

    // 1) Read .md file
    String markdownText = readFile(mdPath);   // real call

    // My empty-file check should go here, inside of main, after the readFile functionality 
    if (markdownText.isEmpty()) {
      System.out.println("Warning: The file appears to be empty.");
      // decide: continue or bail
      // System.exit(1);
    }

    // TEMP: prove it works
    System.out.println(markdownText);

    // 2) Convert Markdown → HTML (TODO: replace with real converter call)
    String htmlBody = /* CodedDifferentlyMarkdown.convert(markdownText) */ null;

    // 3) Wrap HTML in full page (TODO)
    String pageHtml = buildPage(htmlBody, useCdnCss, highlight);

    // 4) Write output (TODO)
    /* writeFile(outputPath, pageHtml); */

    // 5) Done message (after you implement writeFile)
    // System.out.println("Wrote " + outputPath + " — open it in your browser!");
  }

  // ---------- Helper Functions for Converter ----------
  static String readFile(String path) {
    try {
      return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println("Sorry, the file couldn't be read: " + path);
      System.err.println("Reason: " + e.getMessage());
      System.exit(1);
      return null; // required by compiler
    }
  }

  static String buildPage(String bodyHtml, boolean useCdnCss, boolean highlight) {
    String cssLink = useCdnCss
        ? "https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.2.0/github-markdown.min.css"
        : "file:./github-markdown.min.css";
   
        // TODO: build and return the page string
    return null;
  }

  static void writeFile(String path, String contents) {
    // TODO: write UTF-8 text to path
  }
}
