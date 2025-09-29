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

    // Accordion Component Functionality
    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentElement;
            const accordionContent = accordionItem.querySelector('.accordion-content');
            const toggle = this.querySelector('.accordion-toggle');
            const isActive = this.classList.contains('active');
            
            // Close all accordion items
            accordionHeaders.forEach(h => {
                h.classList.remove('active');
                h.parentElement.querySelector('.accordion-content').classList.remove('active');
                h.querySelector('.accordion-toggle').textContent = '+';
            });
            
            // If this item wasn't active, open it
            if (!isActive) {
                this.classList.add('active');
                accordionContent.classList.add('active');
                toggle.textContent = '×';
            }
        });
    });

    // Photo Gallery Functionality
    const galleryTrack = document.querySelector('.gallery-track');
    const gallerySlides = document.querySelectorAll('.gallery-slide');
    const prevBtn = document.querySelector('.prev-btn');
    const nextBtn = document.querySelector('.next-btn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentPosition = 0;
    const totalSlides = gallerySlides.length;
    
    // Determine slides per view based on screen size
    function getSlidesPerView() {
        return window.innerWidth <= 768 ? 1 : 3;
    }
    
    // Calculate maximum position based on slides per view
    function getMaxPosition() {
        const slidesPerView = getSlidesPerView();
        return Math.max(0, totalSlides - slidesPerView);
    }
    
    // Update gallery position
    function updateGallery() {
        const slidesPerView = getSlidesPerView();
        const slideWidth = 100 / slidesPerView;
        const translateX = -currentPosition * slideWidth;
        
        galleryTrack.style.transform = `translateX(${translateX}%)`;
        // Update indicators
        indicators.forEach((indicator, index) => {
            indicator.classList.toggle('active', index === Math.floor(currentPosition / slidesPerView));
        });
    }
    
    // Next button functionality
    nextBtn.addEventListener('click', function() {
        const maxPosition = getMaxPosition();
        if (currentPosition < maxPosition) {
            currentPosition++;
            updateGallery();
        }
    });
    
    // Previous button functionality
    prevBtn.addEventListener('click', function() {
        if (currentPosition > 0) {
            currentPosition--;
            updateGallery();
        }
    });
    
    // Indicator functionality
    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', function() {
            const slidesPerView = getSlidesPerView();
            currentPosition = index * slidesPerView;
            
            // Ensure we don't exceed maximum position
            const maxPosition = getMaxPosition();
            currentPosition = Math.min(currentPosition, maxPosition);
            
            updateGallery();
        });
    });
    
    // Handle window resize
    window.addEventListener('resize', function() {
        // Reset position if it exceeds new maximum
        const maxPosition = getMaxPosition();
        if (currentPosition > maxPosition) {
            currentPosition = maxPosition;
        }
        updateGallery();
    });
    
    // Initialize gallery
    updateGallery();
});