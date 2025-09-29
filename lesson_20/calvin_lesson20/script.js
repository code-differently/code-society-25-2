// Interactive UI Components JavaScript
// Pure vanilla JavaScript - no external libraries

document.addEventListener('DOMContentLoaded', function() {
    // Initialize all components
    initTabs();
    initAccordion();
    initPhotoGallery();
});

// ================================
// TABBED COMPONENT FUNCTIONALITY
// ================================

function initTabs() {
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
        
        // Keyboard navigation support
        button.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });
    });
}

// ================================
// ACCORDION COMPONENT FUNCTIONALITY
// ================================

function initAccordion() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');
    
    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentNode;
            const accordionContent = accordionItem.querySelector('.accordion-content');
            const isActive = this.classList.contains('active');
            
            // Close all accordion items
            accordionHeaders.forEach(otherHeader => {
                otherHeader.classList.remove('active');
                otherHeader.parentNode.querySelector('.accordion-content').classList.remove('active');
            });
            
            // If the clicked item wasn't active, open it
            if (!isActive) {
                this.classList.add('active');
                accordionContent.classList.add('active');
            }
        });
        
        // Keyboard navigation support
        header.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });
    });
}

// ================================
// PHOTO GALLERY FUNCTIONALITY
// ================================

function initPhotoGallery() {
    const galleryTrack = document.querySelector('.gallery-track');
    const galleryItems = document.querySelectorAll('.gallery-item');
    const prevBtn = document.querySelector('.prev-btn');
    const nextBtn = document.querySelector('.next-btn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentSlide = 0;
    const itemsPerSlide = 3;
    const totalSlides = Math.ceil(galleryItems.length / itemsPerSlide);
    
    // Function to update gallery position
    function updateGallery() {
        const translateX = -currentSlide * 100;
        galleryTrack.style.transform = `translateX(${translateX}%)`;
        
        // Update indicators
        indicators.forEach((indicator, index) => {
            indicator.classList.toggle('active', index === currentSlide);
        });
        
        // Update button states
        prevBtn.disabled = currentSlide === 0;
        nextBtn.disabled = currentSlide === totalSlides - 1;
        
        // Update ARIA attributes for accessibility
        updateGalleryAria();
    }
    
    // Function to update ARIA attributes
    function updateGalleryAria() {
        galleryItems.forEach((item, index) => {
            const slideIndex = Math.floor(index / itemsPerSlide);
            const isVisible = slideIndex === currentSlide;
            item.setAttribute('aria-hidden', !isVisible);
        });
        
        prevBtn.setAttribute('aria-label', `Previous images (slide ${currentSlide} of ${totalSlides})`);
        nextBtn.setAttribute('aria-label', `Next images (slide ${currentSlide + 2} of ${totalSlides})`);
    }
    
    // Previous button functionality
    prevBtn.addEventListener('click', function() {
        if (currentSlide > 0) {
            currentSlide--;
            updateGallery();
        }
    });
    
    // Next button functionality
    nextBtn.addEventListener('click', function() {
        if (currentSlide < totalSlides - 1) {
            currentSlide++;
            updateGallery();
        }
    });
    
    // Indicator functionality
    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', function() {
            currentSlide = index;
            updateGallery();
        });
        
        // Keyboard navigation for indicators
        indicator.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });
    });
    
    // Keyboard navigation for gallery
    document.addEventListener('keydown', function(e) {
        // Only handle arrow keys when focus is on gallery elements
        const galleryContainer = document.querySelector('.gallery-container');
        if (galleryContainer.contains(document.activeElement)) {
            if (e.key === 'ArrowLeft' && !prevBtn.disabled) {
                e.preventDefault();
                prevBtn.click();
            } else if (e.key === 'ArrowRight' && !nextBtn.disabled) {
                e.preventDefault();
                nextBtn.click();
            }
        }
    });
    
    // Touch/swipe support for mobile
    let startX = null;
    let endX = null;
    
    galleryTrack.addEventListener('touchstart', function(e) {
        startX = e.touches[0].clientX;
    }, { passive: true });
    
    galleryTrack.addEventListener('touchmove', function(e) {
        if (startX) {
            endX = e.touches[0].clientX;
        }
    }, { passive: true });
    
    galleryTrack.addEventListener('touchend', function(e) {
        if (startX && endX) {
            const difference = startX - endX;
            const threshold = 50; // Minimum swipe distance
            
            if (Math.abs(difference) > threshold) {
                if (difference > 0 && !nextBtn.disabled) {
                    // Swiped left - go to next
                    nextBtn.click();
                } else if (difference < 0 && !prevBtn.disabled) {
                    // Swiped right - go to previous
                    prevBtn.click();
                }
            }
        }
        startX = null;
        endX = null;
    }, { passive: true });
    
    // Auto-play functionality (optional - can be enabled)
    let autoPlayInterval;
    const autoPlayDelay = 5000; // 5 seconds
    
    function startAutoPlay() {
        autoPlayInterval = setInterval(() => {
            if (currentSlide < totalSlides - 1) {
                currentSlide++;
            } else {
                currentSlide = 0; // Loop back to start
            }
            updateGallery();
        }, autoPlayDelay);
    }
    
    function stopAutoPlay() {
        if (autoPlayInterval) {
            clearInterval(autoPlayInterval);
            autoPlayInterval = null;
        }
    }
    
    // Auto-play controls (pause on hover/focus, resume on leave)
    const galleryContainer = document.querySelector('.gallery-container');
    
    galleryContainer.addEventListener('mouseenter', stopAutoPlay);
    galleryContainer.addEventListener('mouseleave', startAutoPlay);
    galleryContainer.addEventListener('focusin', stopAutoPlay);
    galleryContainer.addEventListener('focusout', startAutoPlay);
    
    // Pause auto-play when page is not visible
    document.addEventListener('visibilitychange', function() {
        if (document.hidden) {
            stopAutoPlay();
        } else {
            startAutoPlay();
        }
    });
    
    // Responsive gallery adjustment
    function adjustGalleryForMobile() {
        const isMobile = window.innerWidth <= 768;
        const galleryItems = document.querySelectorAll('.gallery-item');
        
        if (isMobile) {
            // On mobile, show 1 item per slide
            galleryItems.forEach(item => {
                item.style.minWidth = '100%';
            });
        } else {
            // On desktop, show 3 items per slide
            galleryItems.forEach(item => {
                item.style.minWidth = 'calc(33.333% - 0.67rem)';
            });
        }
        
        // Recalculate total slides
        const newItemsPerSlide = isMobile ? 1 : 3;
        const newTotalSlides = Math.ceil(galleryItems.length / newItemsPerSlide);
        
        // Adjust current slide if necessary
        if (currentSlide >= newTotalSlides) {
            currentSlide = newTotalSlides - 1;
        }
        
        updateGallery();
    }
    
    // Listen for window resize
    window.addEventListener('resize', debounce(adjustGalleryForMobile, 250));
    
    // Initialize gallery
    updateGallery();
    adjustGalleryForMobile();
    
    // Start auto-play (uncomment to enable)
    // startAutoPlay();
}

// ================================
// UTILITY FUNCTIONS
// ================================

// Debounce function to limit how often a function can be called
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Function to handle smooth scrolling (if needed for navigation)
function smoothScrollTo(element, duration = 500) {
    const targetPosition = element.offsetTop;
    const startPosition = window.pageYOffset;
    const distance = targetPosition - startPosition;
    let startTime = null;
    
    function animation(currentTime) {
        if (startTime === null) startTime = currentTime;
        const timeElapsed = currentTime - startTime;
        const run = easeInOutQuad(timeElapsed, startPosition, distance, duration);
        window.scrollTo(0, run);
        if (timeElapsed < duration) requestAnimationFrame(animation);
    }
    
    function easeInOutQuad(t, b, c, d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t + b;
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }
    
    requestAnimationFrame(animation);
}

// Enhanced accessibility features
document.addEventListener('DOMContentLoaded', function() {
    // Add skip links for keyboard navigation
    addSkipLinks();
    
    // Announce dynamic content changes to screen readers
    setupAriaLiveRegions();
});

function addSkipLinks() {
    const skipLink = document.createElement('a');
    skipLink.href = '#main-content';
    skipLink.textContent = 'Skip to main content';
    skipLink.className = 'skip-link';
    
    // Add styles for skip link
    const skipLinkStyles = `
        .skip-link {
            position: absolute;
            top: -40px;
            left: 6px;
            background: #000;
            color: #fff;
            padding: 8px;
            text-decoration: none;
            border-radius: 0 0 4px 4px;
            z-index: 10000;
            transition: top 0.3s;
        }
        .skip-link:focus {
            top: 0;
        }
    `;
    
    const style = document.createElement('style');
    style.textContent = skipLinkStyles;
    document.head.appendChild(style);
    
    document.body.insertBefore(skipLink, document.body.firstChild);
    
    // Add id to main content
    const main = document.querySelector('main');
    if (main) {
        main.id = 'main-content';
        main.setAttribute('tabindex', '-1');
    }
}

function setupAriaLiveRegions() {
    // Create a live region for announcing changes
    const liveRegion = document.createElement('div');
    liveRegion.setAttribute('aria-live', 'polite');
    liveRegion.setAttribute('aria-atomic', 'true');
    liveRegion.className = 'sr-only';
    liveRegion.style.cssText = `
        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip: rect(0, 0, 0, 0);
        white-space: nowrap;
        border: 0;
    `;
    
    document.body.appendChild(liveRegion);
    
    // Function to announce changes
    window.announceChange = function(message) {
        liveRegion.textContent = message;
        setTimeout(() => {
            liveRegion.textContent = '';
        }, 1000);
    };
}

// Console log for successful initialization
console.log('Interactive UI Components initialized successfully!');
console.log('Components loaded:', {
    tabs: document.querySelectorAll('.tab-button').length + ' tabs',
    accordion: document.querySelectorAll('.accordion-item').length + ' accordion items',
    gallery: document.querySelectorAll('.gallery-item').length + ' gallery items'
});