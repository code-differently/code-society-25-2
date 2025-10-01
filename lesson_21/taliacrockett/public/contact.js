document.getElementById('contactForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    
    // Show loading state
    const submitButton = this.querySelector('.submit-button');
    const originalText = submitButton.textContent;
    submitButton.textContent = 'Sending...';
    submitButton.disabled = true;
    
    try {
        const response = await fetch('/contact', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const result = await response.json();
        
        const messageDiv = document.getElementById('response-message');
        messageDiv.style.display = 'block';
        messageDiv.innerHTML = `
            <h3>Thank you for your message!</h3>
            <p>${result.message}</p>
            <p><strong>Your submitted information:</strong></p>
            <ul>
                <li><strong>Name:</strong> ${data.name}</li>
                <li><strong>Email:</strong> ${data.email}</li>
                ${data.phone ? `<li><strong>Phone:</strong> ${data.phone}</li>` : ''}
                <li><strong>Subject:</strong> ${data.subject}</li>
                <li><strong>Message:</strong> ${data.message}</li>
            </ul>
        `;
        
        // Reset form
        this.reset();
        
        // Scroll to the response message
        messageDiv.scrollIntoView({ behavior: 'smooth' });
        
    } catch (error) {
        console.error('Error:', error);
        const messageDiv = document.getElementById('response-message');
        messageDiv.style.display = 'block';
        messageDiv.innerHTML = '<p style="color: red;">There was an error sending your message. Please try again.</p>';
    } finally {
        // Restore button state
        submitButton.textContent = originalText;
        submitButton.disabled = false;
    }
});