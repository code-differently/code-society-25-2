// JavaScript for interactive components
console.log("JavaScript file loaded successfully!");

// Tabbed Component Functionality
document.addEventListener('DOMContentLoaded', function() {
    // Get all tab buttons
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabPanels = document.querySelectorAll('.tab-panel');
    
    // Add click event to each tab button
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Remove active class from all buttons and panels
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanels.forEach(panel => panel.classList.remove('active'));
            
            // Add active class to clicked button
            this.classList.add('active');
            
            // Show the corresponding panel
            const targetTab = this.getAttribute('data-tab');
            document.getElementById(targetTab).classList.add('active');
        });
    });
});
// Accordion Component Functionality
const accordionHeaders = document.querySelectorAll('.accordion-header');

accordionHeaders.forEach(header => {
    header.addEventListener('click', function() {
        const accordionItem = this.parentElement;
        const accordionContent = accordionItem.querySelector('.accordion-content');
        const isActive = this.classList.contains('active');
        
        // Close all accordion items
        accordionHeaders.forEach(h => {
            h.classList.remove('active');
            h.parentElement.querySelector('.accordion-content').classList.remove('active');
        });
        
        // If this item wasn't active, open it
        if (!isActive) {
            this.classList.add('active');
            accordionContent.classList.add('active');
        }
    });
});