const headers = document.querySelectorAll(".accordion header");

    headers.forEach(header => {
      header.addEventListener("click", () => {
        const content = header.nextElementSibling;
        content.classList.toggle("open");
      });
    });