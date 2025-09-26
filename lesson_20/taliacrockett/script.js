const headers = document.querySelectorAll('.accordion header');
headers.forEach(header => {
    header.addEventListener('click', (evt) => {
        header.parentElement.classList.toggle('collapsed');
    });
});