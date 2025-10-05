// Wait for DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    initializeTabs();
    initializeAccordion();
    initializeGallery();
});

// Tabbed Component Functionality
function initializeTabs() {
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabPanels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            
            // Remove active class from all buttons and panels
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanels.forEach(panel => panel.classList.remove('active'));
            
            // Add active class to clicked button and corresponding panel
            this.classList.add('active');
            document.getElementById(targetTab).classList.add('active');
        });
    });
}

// Accordion Component Functionality
function initializeAccordion() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentNode;
            const accordionContent = accordionItem.querySelector('.accordion-content');
            const isActive = this.classList.contains('active');

            // Toggle the current accordion item
            if (isActive) {
                this.classList.remove('active');
                accordionContent.classList.remove('active');
            } else {
                this.classList.add('active');
                accordionContent.classList.add('active');
            }
        });
    });
}

// Photo Gallery Functionality
function initializeGallery() {
    const galleryTrack = document.querySelector('.gallery-track');
    const slides = document.querySelectorAll('.gallery-slide');
    const prevBtn = document.querySelector('.prev-btn');
    const nextBtn = document.querySelector('.next-btn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentIndex = 0;
    const totalSlides = slides.length;
    const slidesToShow = getSlidesToShow();
    const maxIndex = Math.max(0, totalSlides - slidesToShow);

    function getSlidesToShow() {
        if (window.innerWidth <= 480) {
            return 1; // Mobile: show 1 slide
        } else if (window.innerWidth <= 768) {
            return 2; // Tablet: show 2 slides
        } else {
            return 3; // Desktop: show 3 slides
        }
    }

    function updateGallery() {
        const slideWidth = 100 / slidesToShow;
        const translateX = -(currentIndex * slideWidth);
        galleryTrack.style.transform = `translateX(${translateX}%)`;
        
        // Update indicators
        indicators.forEach((indicator, index) => {
            indicator.classList.toggle('active', index === currentIndex);
        });
        
        // Update button states
        prevBtn.disabled = currentIndex === 0;
        nextBtn.disabled = currentIndex >= maxIndex;
    }

    function goToSlide(index) {
        currentIndex = Math.max(0, Math.min(index, maxIndex));
        updateGallery();
    }

    function nextSlide() {
        if (currentIndex < maxIndex) {
            currentIndex++;
            updateGallery();
        }
    }

    function prevSlide() {
        if (currentIndex > 0) {
            currentIndex--;
            updateGallery();
        }
    }

    // Event listeners
    nextBtn.addEventListener('click', nextSlide);
    prevBtn.addEventListener('click', prevSlide);

    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', () => goToSlide(index));
    });

    // Handle window resize
    window.addEventListener('resize', function() {
        const newSlidesToShow = getSlidesToShow();
        if (newSlidesToShow !== slidesToShow) {
            location.reload(); // Simple solution: reload page on significant resize
        }
    });

    // Auto-rotation (optional)
    let autoRotateInterval;
    
    function startAutoRotate() {
        autoRotateInterval = setInterval(() => {
            if (currentIndex >= maxIndex) {
                goToSlide(0); // Go back to start
            } else {
                nextSlide();
            }
        }, 3000); // Change slide every 3 seconds
    }

    function stopAutoRotate() {
        clearInterval(autoRotateInterval);
    }

    // Start auto-rotation
    startAutoRotate();

    // Pause auto-rotation on hover
    const galleryContainer = document.querySelector('.gallery-container');
    galleryContainer.addEventListener('mouseenter', stopAutoRotate);
    galleryContainer.addEventListener('mouseleave', startAutoRotate);

    // Pause auto-rotation when user interacts
    [prevBtn, nextBtn, ...indicators].forEach(element => {
        element.addEventListener('click', () => {
            stopAutoRotate();
            setTimeout(startAutoRotate, 5000); // Restart after 5 seconds
        });
    });

    // Initialize gallery
    updateGallery();
}

// Keyboard Navigation Support
document.addEventListener('keydown', function(e) {
    // Tab navigation for tabs component
    if (e.target.classList.contains('tab-button')) {
        if (e.key === 'ArrowLeft' || e.key === 'ArrowRight') {
            e.preventDefault();
            const tabButtons = Array.from(document.querySelectorAll('.tab-button'));
            const currentIndex = tabButtons.indexOf(e.target);
            let nextIndex;
            
            if (e.key === 'ArrowLeft') {
                nextIndex = currentIndex > 0 ? currentIndex - 1 : tabButtons.length - 1;
            } else {
                nextIndex = currentIndex < tabButtons.length - 1 ? currentIndex + 1 : 0;
            }
            
            tabButtons[nextIndex].focus();
            tabButtons[nextIndex].click();
        }
    }
    
    // Gallery navigation with arrow keys
    if (e.target.closest('.gallery-container')) {
        if (e.key === 'ArrowLeft') {
            e.preventDefault();
            document.querySelector('.prev-btn').click();
        } else if (e.key === 'ArrowRight') {
            e.preventDefault();
            document.querySelector('.next-btn').click();
        }
    }
});

// Touch/Swipe support for mobile gallery
let touchStartX = 0;
let touchEndX = 0;

const galleryViewport = document.querySelector('.gallery-viewport');

galleryViewport.addEventListener('touchstart', function(e) {
    touchStartX = e.changedTouches[0].screenX;
});

galleryViewport.addEventListener('touchend', function(e) {
    touchEndX = e.changedTouches[0].screenX;
    handleSwipe();
});

function handleSwipe() {
    const swipeThreshold = 50;
    const swipeDistance = touchStartX - touchEndX;
    
    if (Math.abs(swipeDistance) > swipeThreshold) {
        if (swipeDistance > 0) {
            // Swipe left - next slide
            document.querySelector('.next-btn').click();
        } else {
            // Swipe right - previous slide
            document.querySelector('.prev-btn').click();
        }
    }
}
