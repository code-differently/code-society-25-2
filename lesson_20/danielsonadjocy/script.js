// ===== TABS =====
const tabButtons = document.querySelectorAll('.tab-button');
const tabContents = document.querySelectorAll('.tab-content');

tabButtons.forEach(button => {
  button.addEventListener('click', () => {
    tabButtons.forEach(b => b.classList.remove('active'));
    tabContents.forEach(c => c.classList.remove('active'));

    button.classList.add('active');
    const targetId = button.getAttribute('data-tab');
    document.getElementById(targetId).classList.add('active');
  });
});

// ===== ACCORDION =====
const accordionButtons = document.querySelectorAll('.accordion-button');

accordionButtons.forEach(button => {
  button.addEventListener('click', () => {
    const content = button.nextElementSibling;
    const isOpen = content.style.display === 'block';

    // Close all others (optional)
    document.querySelectorAll('.accordion-content').forEach(c => c.style.display = 'none');

    // Toggle current
    content.style.display = isOpen ? 'none' : 'block';
  });
});

// ===== GALLERY =====
const gallery = document.querySelector('.gallery');
const galleryImages = gallery.querySelectorAll('img');
const backButton = document.querySelector('.gallery-back-button');
const forwardButton = document.querySelector('.gallery-forward-button');

forwardButton.addEventListener('click', () => {
  gallery.appendChild(gallery.firstElementChild); // move first to end
});

backButton.addEventListener('click', () => {
  gallery.insertBefore(gallery.lastElementChild, gallery.firstElementChild); // move last to start
});
