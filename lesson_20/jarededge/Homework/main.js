
document.addEventListener('DOMContentLoaded', function() {
    
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabPanels = document.querySelectorAll('.tab-panel');

    
    function switchTab(targetTab) {
        
        tabButtons.forEach(button => button.classList.remove('active'));
        tabPanels.forEach(panel => panel.classList.remove('active'));

        
        const clickedButton = document.querySelector(`[data-tab="${targetTab}"]`);
        clickedButton.classList.add('active');

        
        const targetPanel = document.getElementById(targetTab);
        targetPanel.classList.add('active');
    }

    
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            switchTab(targetTab);
        });
    });

    
    tabButtons.forEach((button, index) => {
        button.addEventListener('keydown', function(e) {
            let targetIndex;
            
            switch(e.key) {
                case 'ArrowLeft':
                    e.preventDefault();
                    targetIndex = index > 0 ? index - 1 : tabButtons.length - 1;
                    tabButtons[targetIndex].focus();
                    switchTab(tabButtons[targetIndex].getAttribute('data-tab'));
                    break;
                    
                case 'ArrowRight':
                    e.preventDefault();
                    targetIndex = index < tabButtons.length - 1 ? index + 1 : 0;
                    tabButtons[targetIndex].focus();
                    switchTab(tabButtons[targetIndex].getAttribute('data-tab'));
                    break;
                    
                case 'Home':
                    e.preventDefault();
                    tabButtons[0].focus();
                    switchTab(tabButtons[0].getAttribute('data-tab'));
                    break;
                    
                case 'End':
                    e.preventDefault();
                    tabButtons[tabButtons.length - 1].focus();
                    switchTab(tabButtons[tabButtons.length - 1].getAttribute('data-tab'));
                    break;
            }
        });
    });

    
    tabButtons.forEach((button, index) => {
        button.setAttribute('role', 'tab');
        button.setAttribute('aria-selected', button.classList.contains('active'));
        button.setAttribute('tabindex', button.classList.contains('active') ? '0' : '-1');
    });

    tabPanels.forEach(panel => {
        panel.setAttribute('role', 'tabpanel');
        panel.setAttribute('aria-hidden', !panel.classList.contains('active'));
    });

    console.log('Nature Explorer tabbed component initialized successfully!');

    // Accordion Component 
    const accordionHeaders = document.querySelectorAll('.accordion-header');
    
    
    function toggleAccordion(header) {
        const content = header.nextElementSibling;
        const icon = header.querySelector('.accordion-icon');
        const isExpanded = header.getAttribute('aria-expanded') === 'true';
        
       
        accordionHeaders.forEach(otherHeader => {
            if (otherHeader !== header) {
                const otherContent = otherHeader.nextElementSibling;
                const otherIcon = otherHeader.querySelector('.accordion-icon');
                
                otherHeader.setAttribute('aria-expanded', 'false');
                otherContent.classList.remove('expanded');
                otherIcon.textContent = '+';
            }
        });
        
        
        if (isExpanded) {
            header.setAttribute('aria-expanded', 'false');
            content.classList.remove('expanded');
            icon.textContent = '+';
        } else {
            header.setAttribute('aria-expanded', 'true');
            content.classList.add('expanded');
            icon.textContent = '−';
        }
    }
    
   
    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            toggleAccordion(this);
        });
        
        
        header.addEventListener('keydown', function(e) {
            switch(e.key) {
                case 'Enter':
                case ' ':
                    e.preventDefault();
                    toggleAccordion(this);
                    break;
                    
                case 'ArrowDown':
                    e.preventDefault();
                    const nextHeader = this.parentElement.nextElementSibling?.querySelector('.accordion-header');
                    if (nextHeader) {
                        nextHeader.focus();
                    } else {
                        // Go to first item if at last
                        accordionHeaders[0].focus();
                    }
                    break;
                    
                case 'ArrowUp':
                    e.preventDefault();
                    const prevHeader = this.parentElement.previousElementSibling?.querySelector('.accordion-header');
                    if (prevHeader) {
                        prevHeader.focus();
                    } else {
                        // Go to last item if at first
                        accordionHeaders[accordionHeaders.length - 1].focus();
                    }
                    break;
                    
                case 'Home':
                    e.preventDefault();
                    accordionHeaders[0].focus();
                    break;
                    
                case 'End':
                    e.preventDefault();
                    accordionHeaders[accordionHeaders.length - 1].focus();
                    break;
            }
        });
    });
    
    console.log('Nature Explorer accordion component initialized successfully!');

    // Carousel Component Functionality
    const carousel = document.querySelector('.carousel');
    const carouselTrack = document.querySelector('.carousel-track');
    const allSlides = document.querySelectorAll('.carousel-slide');
    const originalSlides = document.querySelectorAll('.carousel-slide:not(.carousel-clone)');
    const prevBtn = document.querySelector('.carousel-btn-prev');
    const nextBtn = document.querySelector('.carousel-btn-next');
    const carouselDots = document.querySelectorAll('.carousel-dot');
    
    let currentSlide = 3; 
    let slidesToShow = 3;
    let slideWidth = 0;
    let isTransitioning = false;
    const cloneCount = 3; 
    
    
    function calculateSlidesToShow() {
        const viewportWidth = window.innerWidth;
        if (viewportWidth <= 480) {
            slidesToShow = 1;
        } else if (viewportWidth <= 768) {
            slidesToShow = 2;
        } else {
            slidesToShow = 3;
        }
    }
    
   
    function updateCarouselLayout() {
        calculateSlidesToShow();
        slideWidth = allSlides[0].offsetWidth + 20; 
        
        
        updateCarouselPosition();
    }
    
    
    function updateCarouselPosition(withTransition = true) {
        const translateX = -currentSlide * slideWidth;
        
        if (withTransition) {
            carouselTrack.style.transition = 'transform 0.5s ease-in-out';
        } else {
            carouselTrack.style.transition = 'none';
        }
        
        carouselTrack.style.transform = `translateX(${translateX}px)`;
        
        
        let actualSlideIndex = currentSlide - cloneCount; 
        if (actualSlideIndex < 0) actualSlideIndex = originalSlides.length + actualSlideIndex;
        if (actualSlideIndex >= originalSlides.length) actualSlideIndex = actualSlideIndex - originalSlides.length;
        
        carouselDots.forEach((dot, index) => {
            dot.classList.toggle('active', index === actualSlideIndex);
        });
        
        
        prevBtn.disabled = false;
        nextBtn.disabled = false;
        prevBtn.style.opacity = '1';
        nextBtn.style.opacity = '1';
    }
    
    
    function handleInfiniteLoop() {
        if (isTransitioning) return;
        
        
        if (currentSlide >= cloneCount + originalSlides.length) {
            isTransitioning = true;
            setTimeout(() => {
                currentSlide = cloneCount; 
                updateCarouselPosition(false); 
                setTimeout(() => {
                    isTransitioning = false;
                }, 50);
            }, 500); 
        }
        
        else if (currentSlide < cloneCount) {
            isTransitioning = true;
            setTimeout(() => {
                currentSlide = cloneCount + originalSlides.length - 1; 
                updateCarouselPosition(false); 
                setTimeout(() => {
                    isTransitioning = false;
                }, 50);
            }, 500); 
        }
    }
    
    
    function goToSlide(slideIndex) {
        if (isTransitioning) return;
        currentSlide = slideIndex + cloneCount; 
        updateCarouselPosition();
    }
    
    // Function to go to next photo 
    function nextSlide() {
        if (isTransitioning) return;
        currentSlide++;
        updateCarouselPosition();
        handleInfiniteLoop();
    }
    
    // Function to go to previous photo 
    function prevSlide() {
        if (isTransitioning) return;
        currentSlide--;
        updateCarouselPosition();
        handleInfiniteLoop();
    }
    
    
    nextBtn.addEventListener('click', nextSlide);
    prevBtn.addEventListener('click', prevSlide);
    
    
    carouselDots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
            goToSlide(index);
        });
    });
    
   
    
    
    let autoPlayInterval;
    const autoPlayDelay = 5000; 
    
    function startAutoPlay() {
        autoPlayInterval = setInterval(() => {
            nextSlide(); 
        }, autoPlayDelay);
    }
    
    function stopAutoPlay() {
        clearInterval(autoPlayInterval);
    }
    
    
    carousel.addEventListener('mouseenter', stopAutoPlay);
    carousel.addEventListener('mouseleave', startAutoPlay);
    carousel.addEventListener('focus', stopAutoPlay);
    carousel.addEventListener('blur', startAutoPlay);
    
    
    updateCarouselLayout();
    
    
    setTimeout(() => {
        updateCarouselPosition(false);
    }, 100);
    
    window.addEventListener('resize', updateCarouselLayout);
    
    
    startAutoPlay();
    
    console.log('Nature Explorer carousel component initialized successfully!');
});