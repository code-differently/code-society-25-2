// JavaScript for Interactive Components

// Tab functionality
function showTab(tabIndex) {
    // Get all tab links and content
    const tabLinks = document.querySelectorAll('.tab-link');
    const tabContents = document.querySelectorAll('.tab-content');
    
    // Remove active class from all tabs and content
    tabLinks.forEach(link => link.classList.remove('active'));
    tabContents.forEach(content => content.classList.remove('active'));
    
    // Add active class to selected tab and content
    tabLinks[tabIndex].classList.add('active');
    document.getElementById(`tab-${tabIndex}`).classList.add('active');
}

// Accordion functionality
document.addEventListener('DOMContentLoaded', function() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');
    
    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentElement;
            const content = accordionItem.querySelector('.accordion-content');
            
            // Toggle active class
            accordionItem.classList.toggle('active');
            
            // Close other accordion items (optional - remove if you want multiple open)
            accordionHeaders.forEach(otherHeader => {
                if (otherHeader !== this) {
                    otherHeader.parentElement.classList.remove('active');
                }
            });
        });
    });
});

// Photo Gallery functionality
let currentGallerySet = 0;
let galleryImages = [];
let imagesPerView = 3;

// Initialize gallery when page loads
document.addEventListener('DOMContentLoaded', function() {
    initializeGallery();
    updateGalleryLayout();
    window.addEventListener('resize', updateGalleryLayout);
});

function initializeGallery() {
    const galleryItems = document.querySelectorAll('.gallery-item');
    galleryImages = Array.from(galleryItems);
    
    updateGalleryInfo();
    updateIndicators();
    updateNavigationButtons();
}

function updateGalleryLayout() {
    const width = window.innerWidth;
    
    // Determine images per view based on screen size
    if (width <= 480) {
        imagesPerView = 1;
    } else if (width <= 768) {
        imagesPerView = 2;
    } else {
        imagesPerView = 3;
    }
    
    // Reset to first set if current set is out of bounds
    const maxSets = Math.ceil(galleryImages.length / imagesPerView);
    if (currentGallerySet >= maxSets) {
        currentGallerySet = 0;
    }
    
    updateGalleryDisplay();
    updateGalleryInfo();
    updateIndicators();
    updateNavigationButtons();
}

function updateGalleryDisplay() {
    const track = document.getElementById('gallery-track');
    if (!track) return;
    
    const translateX = -(currentGallerySet * 100);
    track.style.transform = `translateX(${translateX}%)`;
    
    // Update gallery item widths based on current view
    galleryImages.forEach(item => {
        if (imagesPerView === 1) {
            item.style.flex = '0 0 100%';
        } else if (imagesPerView === 2) {
            item.style.flex = '0 0 calc(50% - 10px)';
        } else {
            item.style.flex = '0 0 calc(33.333% - 14px)';
        }
    });
}

function nextImages() {
    const maxSets = Math.ceil(galleryImages.length / imagesPerView);
    
    // Loop back to beginning when reaching the end
    if (currentGallerySet < maxSets - 1) {
        currentGallerySet++;
    } else {
        currentGallerySet = 0; // Loop back to start
    }
    
    updateGalleryDisplay();
    updateGalleryInfo();
    updateIndicators();
    updateNavigationButtons();
}

function previousImages() {
    const maxSets = Math.ceil(galleryImages.length / imagesPerView);
    
    // Loop to end when going back from beginning
    if (currentGallerySet > 0) {
        currentGallerySet--;
    } else {
        currentGallerySet = maxSets - 1; // Loop to last set
    }
    
    updateGalleryDisplay();
    updateGalleryInfo();
    updateIndicators();
    updateNavigationButtons();
}

function goToSet(setIndex) {
    const maxSets = Math.ceil(galleryImages.length / imagesPerView);
    
    if (setIndex >= 0 && setIndex < maxSets) {
        currentGallerySet = setIndex;
        updateGalleryDisplay();
        updateGalleryInfo();
        updateIndicators();
        updateNavigationButtons();
    }
}

function updateGalleryInfo() {
    const currentImagesSpan = document.getElementById('current-images');
    const totalImagesSpan = document.getElementById('total-images');
    
    if (currentImagesSpan && totalImagesSpan) {
        const startImage = (currentGallerySet * imagesPerView) + 1;
        const endImage = Math.min(startImage + imagesPerView - 1, galleryImages.length);
        
        currentImagesSpan.textContent = `${startImage}-${endImage}`;
        totalImagesSpan.textContent = galleryImages.length;
    }
}

function updateIndicators() {
    const indicators = document.querySelectorAll('.indicator');
    const maxSets = Math.ceil(galleryImages.length / imagesPerView);
    
    // Hide/show indicators based on number of sets needed
    indicators.forEach((indicator, index) => {
        if (index < maxSets) {
            indicator.style.display = 'block';
            indicator.classList.toggle('active', index === currentGallerySet);
        } else {
            indicator.style.display = 'none';
        }
    });
}

function updateNavigationButtons() {
    const prevBtn = document.querySelector('.gallery-prev');
    const nextBtn = document.querySelector('.gallery-next');
    
    // Since we now have looping, navigation buttons are never disabled
    if (prevBtn) {
        prevBtn.disabled = false;
    }
    
    if (nextBtn) {
        nextBtn.disabled = false;
    }
}

// Add keyboard navigation for accessibility
document.addEventListener('keydown', function(e) {
    if (e.target.closest('.photo-gallery-section')) {
        if (e.key === 'ArrowLeft') {
            e.preventDefault();
            previousImages();
        } else if (e.key === 'ArrowRight') {
            e.preventDefault();
            nextImages();
        }
    }
});

// Touch/swipe support for mobile
let touchStartX = 0;
let touchEndX = 0;

document.addEventListener('touchstart', function(e) {
    if (e.target.closest('.gallery-viewport')) {
        touchStartX = e.changedTouches[0].screenX;
    }
});

document.addEventListener('touchend', function(e) {
    if (e.target.closest('.gallery-viewport')) {
        touchEndX = e.changedTouches[0].screenX;
        handleSwipe();
    }
});

function handleSwipe() {
    const swipeThreshold = 50;
    const swipeLength = touchEndX - touchStartX;
    
    if (Math.abs(swipeLength) > swipeThreshold) {
        if (swipeLength > 0) {
            previousImages(); // Swipe right
        } else {
            nextImages(); // Swipe left
        }
    }
}