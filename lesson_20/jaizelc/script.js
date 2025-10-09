// Mobile-Responsive UI Controls JavaScript
// Author: Jaizel Cespedes - Lesson 20 Assignment

document.addEventListener('DOMContentLoaded', function() {
    initTabs();
    initAccordion();
    initGallery();
});

// Tabbed Component Functionality
function initTabs() {
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabPanels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanels.forEach(panel => panel.classList.remove('active'));
            
            this.classList.add('active');
            document.getElementById(targetTab).classList.add('active');
        });
    });

    // Keyboard navigation for tabs
    tabButtons.forEach((button, index) => {
        button.addEventListener('keydown', function(e) {
            let targetIndex;
            
            if (e.key === 'ArrowRight') {
                e.preventDefault();
                targetIndex = (index + 1) % tabButtons.length;
            } else if (e.key === 'ArrowLeft') {
                e.preventDefault();
                targetIndex = (index - 1 + tabButtons.length) % tabButtons.length;
            } else if (e.key === 'Home') {
                e.preventDefault();
                targetIndex = 0;
            } else if (e.key === 'End') {
                e.preventDefault();
                targetIndex = tabButtons.length - 1;
            }
            
            if (targetIndex !== undefined) {
                tabButtons[targetIndex].focus();
                tabButtons[targetIndex].click();
            }
        });
    });
}

// Accordion Component Functionality
function initAccordion() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentElement;
            const accordionContent = accordionItem.querySelector('.accordion-content');
            const isActive = this.classList.contains('active');

            accordionHeaders.forEach(h => {
                h.classList.remove('active');
                h.parentElement.querySelector('.accordion-content').classList.remove('active');
            });

            if (!isActive) {
                this.classList.add('active');
                accordionContent.classList.add('active');
            }
        });

        header.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });
    });
}

// Photo Gallery Functionality
function initGallery() {
    const galleryTrack = document.getElementById('galleryTrack');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicatorsContainer = document.getElementById('galleryIndicators');
    const slides = document.querySelectorAll('.gallery-slide');
    
    let currentIndex = 0;
    let slidesToShow = getSlidesToShow();
    let maxIndex = slides.length - slidesToShow;
    
    createIndicators();
    
    function getSlidesToShow() {
        if (window.innerWidth <= 768) {
            return 1;
        } else if (window.innerWidth <= 1024) {
            return 2;
        } else {
            return 3;
        }
    }
    
    function createIndicators() {
        indicatorsContainer.innerHTML = '';
        const totalPages = Math.ceil(slides.length / slidesToShow);
        
        for (let i = 0; i < totalPages; i++) {
            const indicator = document.createElement('div');
            indicator.classList.add('gallery-indicator');
            if (i === 0) indicator.classList.add('active');
            
            indicator.addEventListener('click', () => {
                goToSlide(i * slidesToShow);
            });
            
            indicatorsContainer.appendChild(indicator);
        }
    }
    
    function updateGallery() {
        const slideWidth = 100 / slidesToShow;
        const translateX = -(currentIndex * slideWidth);
        galleryTrack.style.transform = `translateX(${translateX}%)`;
        
        const indicators = document.querySelectorAll('.gallery-indicator');
        indicators.forEach((indicator, index) => {
            indicator.classList.remove('active');
            if (index === Math.floor(currentIndex / slidesToShow)) {
                indicator.classList.add('active');
            }
        });
        
        prevBtn.disabled = currentIndex === 0;
        nextBtn.disabled = currentIndex >= maxIndex;
        
        if (prevBtn.disabled) {
            prevBtn.style.opacity = '0.5';
            prevBtn.style.cursor = 'not-allowed';
        } else {
            prevBtn.style.opacity = '1';
            prevBtn.style.cursor = 'pointer';
        }
        
        if (nextBtn.disabled) {
            nextBtn.style.opacity = '0.5';
            nextBtn.style.cursor = 'not-allowed';
        } else {
            nextBtn.style.opacity = '1';
            nextBtn.style.cursor = 'pointer';
        }
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
    
    nextBtn.addEventListener('click', nextSlide);
    prevBtn.addEventListener('click', prevSlide);
    
    // Keyboard navigation
    document.addEventListener('keydown', function(e) {
        if (e.target.closest('.gallery-container')) {
            if (e.key === 'ArrowRight') {
                e.preventDefault();
                nextSlide();
            } else if (e.key === 'ArrowLeft') {
                e.preventDefault();
                prevSlide();
            }
        }
    });
    
    // Touch/swipe support
    let startX = 0;
    let endX = 0;
    
    galleryTrack.addEventListener('touchstart', function(e) {
        startX = e.touches[0].clientX;
    });
    
    galleryTrack.addEventListener('touchmove', function(e) {
        e.preventDefault();
    });
    
    galleryTrack.addEventListener('touchend', function(e) {
        endX = e.changedTouches[0].clientX;
        handleSwipe();
    });
    
    function handleSwipe() {
        const swipeThreshold = 50;
        const swipeDistance = startX - endX;
        
        if (Math.abs(swipeDistance) > swipeThreshold) {
            if (swipeDistance > 0) {
                nextSlide();
            } else {
                prevSlide();
            }
        }
    }
    
    // Auto-rotate gallery
    let autoRotateInterval;
    
    function startAutoRotate() {
        autoRotateInterval = setInterval(() => {
            if (currentIndex >= maxIndex) {
                goToSlide(0);
            } else {
                nextSlide();
            }
        }, 5000);
    }
    
    function stopAutoRotate() {
        clearInterval(autoRotateInterval);
    }
    
    startAutoRotate();
    
    const galleryContainer = document.querySelector('.gallery-container');
    galleryContainer.addEventListener('mouseenter', stopAutoRotate);
    galleryContainer.addEventListener('mouseleave', startAutoRotate);
    galleryContainer.addEventListener('touchstart', stopAutoRotate);
    
    function handleResize() {
        const newSlidesToShow = getSlidesToShow();
        if (newSlidesToShow !== slidesToShow) {
            slidesToShow = newSlidesToShow;
            maxIndex = slides.length - slidesToShow;
            
            if (currentIndex > maxIndex) {
                currentIndex = maxIndex;
            }
            
            createIndicators();
            updateGallery();
        }
    }
    
    // Debounced resize handler
    let resizeTimeout;
    window.addEventListener('resize', function() {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(handleResize, 250);
    });
    
    updateGallery();
}

function addSmoothScrolling() {
    const anchorLinks = document.querySelectorAll('a[href^="#"]');
    
    anchorLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);
            
            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

addSmoothScrolling();

document.addEventListener('keydown', function(e) {
    if (e.key === 'Tab') {
        const focusableElements = document.querySelectorAll(
            'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])'
        );
    }
});

// Intersection Observer for lazy loading
if ('IntersectionObserver' in window) {
    const imageObserver = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const img = entry.target;
                if (img.dataset.src) {
                    img.src = img.dataset.src;
                    img.removeAttribute('data-src');
                    observer.unobserve(img);
                }
            }
        });
    });
    
    document.querySelectorAll('img[data-src]').forEach(img => {
        imageObserver.observe(img);
    });
}
