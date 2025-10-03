document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.signup-form');
    const submitButton = document.getElementById('submitBtn');
    
    // Format phone number as user types
    const phoneInput = document.getElementById('phone');
    phoneInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length >= 6) {
            value = value.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
        } else if (value.length >= 3) {
            value = value.replace(/(\d{3})(\d{3})/, '($1) $2');
        }
        e.target.value = value;
    });
    
    // Add loading state to submit button
    form.addEventListener('submit', function() {
        submitButton.textContent = 'Processing...';
        submitButton.disabled = true;
    });
    
    // Add visual feedback for required fields
    const requiredFields = form.querySelectorAll('[required]');
    requiredFields.forEach(field => {
        field.addEventListener('blur', function() {
            if (!this.value.trim()) {
                this.style.borderColor = '#dc3545';
            } else {
                this.style.borderColor = '#28a745';
            }
        });
    });
    
    // Enable/disable submit button based on terms checkbox
    const termsCheckbox = document.getElementById('agreeToTerms');
    const updateSubmitButton = () => {
        if (termsCheckbox.checked) {
            submitButton.disabled = false;
            submitButton.style.opacity = '1';
        } else {
            submitButton.disabled = true;
            submitButton.style.opacity = '0.6';
        }
    };
    
    termsCheckbox.addEventListener('change', updateSubmitButton);
    updateSubmitButton(); // Initial state
    
    // Form validation feedback
    form.addEventListener('submit', function(e) {
        const requiredFields = form.querySelectorAll('[required]');
        let isValid = true;
        
        requiredFields.forEach(field => {
            if (!field.value.trim()) {
                field.style.borderColor = '#dc3545';
                isValid = false;
            }
        });
        
        if (!isValid) {
            e.preventDefault();
            submitButton.textContent = 'Join Code Differently';
            submitButton.disabled = false;
            alert('Please fill out all required fields.');
        }
    });
});
