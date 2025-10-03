document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signupForm');
    const message = document.getElementById('formMessage');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        const name = form.name.value.trim();
        const email = form.email.value.trim();
        const password = form.password.value;

        fetch('/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, email, password })
        })
        .then(response => response.json())
        .then(data => {
            window.location.href = `/signup/signupdone.html?name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`;
        })
        .catch(error => {
            message.textContent = 'Error submitting form.';
            message.classList.add('show');
            setTimeout(() => message.classList.remove('show'), 3500);
        });
    });
});
