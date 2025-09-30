document.addEventListener('DOMContentLoaded', function() {
    // Parse URL parameters to get the submitted data
    const urlParams = new URLSearchParams(window.location.search);
    
    // Update the data display with the submitted information
    const dataElements = {
        'firstName': document.getElementById('data-firstName'),
        'lastName': document.getElementById('data-lastName'),
        'email': document.getElementById('data-email'),
        'phone': document.getElementById('data-phone'),
        'program': document.getElementById('data-program'),
        'experience': document.getElementById('data-experience'),
        'goals': document.getElementById('data-goals'),
        'agreeToTerms': document.getElementById('data-agreeToTerms')
    };
    
    // Populate the data fields
    Object.keys(dataElements).forEach(key => {
        const element = dataElements[key];
        if (element) {
            const value = urlParams.get(key);
            if (key === 'agreeToTerms') {
                element.textContent = value === 'on' ? 'Yes' : 'No';
            } else {
                element.textContent = value || 'Not provided';
            }
        }
    });
    
    // Add some celebratory animations
    const welcomeMessage = document.querySelector('.welcome-message');
    if (welcomeMessage) {
        setTimeout(() => {
            welcomeMessage.style.animation = 'pulse 0.5s ease-in-out';
        }, 500);
    }
    
    // Add click tracking for buttons (optional analytics)
    const buttons = document.querySelectorAll('.back-button');
    buttons.forEach(button => {
        button.addEventListener('click', function(e) {
            console.log('Button clicked:', this.textContent);
        });
    });
});
