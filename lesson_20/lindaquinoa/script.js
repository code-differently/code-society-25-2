document.addEventListener('DOMContentLoaded', function() {
    initializeTabs();
    initializeAccordion();
    initializeGallery();
});
function initializeTabs() {
    const tabButtons = document.querySelectorAll('.tab-button');
    
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            
            tabButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            
            const tabPanels = document.querySelectorAll('.tab-panel');
            tabPanels.forEach(panel => panel.classList.remove('active'));
            
            const targetPanel = document.getElementById(targetTab);
            if (targetPanel) {
                targetPanel.classList.add('active');
            }
        });
    });
}

function initializeAccordion() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');
    
    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentElement;
            accordionItem.classList.toggle('active');
        });
    });
}

function initializeGallery() {
    const photoSlides = document.querySelectorAll('.photo-slide');
    const prevButton = document.querySelector('.prev-btn');
    const nextButton = document.querySelector('.next-btn');
    
    let currentSlide = 0;
    
    function showSlide(slideIndex) {
        photoSlides.forEach(slide => slide.classList.remove('active'));
        if (photoSlides[slideIndex]) {
            photoSlides[slideIndex].classList.add('active');
        }
    }
    
    prevButton.addEventListener('click', function() {
        currentSlide = currentSlide - 1;
        if (currentSlide < 0) {
            currentSlide = photoSlides.length - 1;
        }
        showSlide(currentSlide);
    });
    
    nextButton.addEventListener('click', function() {
        currentSlide = currentSlide + 1;
        if (currentSlide >= photoSlides.length) {
            currentSlide = 0;
        }
        showSlide(currentSlide);
    });
}


