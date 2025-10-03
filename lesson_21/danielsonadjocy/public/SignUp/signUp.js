function showError(message) {
    const errorDiv = document.getElementById('error-message');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
    errorDiv.scrollIntoView({ behavior: 'smooth' });
}

function hideError() {
    const errorDiv = document.getElementById('error-message');
    errorDiv.style.display = 'none';
}

function validateForm() {
    hideError(); // Clear any previous errors
    
    const password = document.querySelector('input[name="password"]').value;
    const confirmPassword = document.querySelector('input[name="confirmPassword"]').value;
    
    if (password !== confirmPassword) {
        showError('Passwords do not match.');
        return false; // Prevent form submission
    }
    
    return true;
}

// Real-time password matching feedback
document.addEventListener('DOMContentLoaded', function() {
    const passwordField = document.querySelector('input[name="password"]');
    const confirmPasswordField = document.querySelector('input[name="confirmPassword"]');
    
    function checkPasswordMatch() {
        if (confirmPasswordField.value && passwordField.value !== confirmPasswordField.value) {
            confirmPasswordField.style.borderColor = '#e74c3c';
        } else {
            confirmPasswordField.style.borderColor = '#e1e1e1';
            hideError();
        }
    }
    
    confirmPasswordField.addEventListener('input', checkPasswordMatch);
    passwordField.addEventListener('input', checkPasswordMatch);
});