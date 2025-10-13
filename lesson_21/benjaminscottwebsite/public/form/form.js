document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('contactForm');
    const responseDiv = document.getElementById('formResponse');

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const name = form.name.value.trim();
        const email = form.email.value.trim();
        const message = form.message.value.trim();

        if (!name || !email || !message) {
            responseDiv.textContent = 'Please fill out all fields.';
            responseDiv.style.color = '#dc3545';
            return;
        }

        // Robust redirect to thankyou.html with form data as query string
        const params = new URLSearchParams({ name, email, message });
        const thankYouPath = window.location.pathname.replace('form.html', 'thankyou.html');
        window.location.href = thankYouPath + '?' + params.toString();
    });
});
