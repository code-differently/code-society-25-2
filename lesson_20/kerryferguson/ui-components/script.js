// Tabs
const tabButtons = document.querySelectorAll('.tab-btn');
const tabContents = document.querySelectorAll('.tab-content');
tabButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    tabButtons.forEach(b => b.classList.remove('active'));
    tabContents.forEach(c => c.classList.remove('active'));
    btn.classList.add('active');
    document.getElementById(btn.dataset.tab).classList.add('active');
  });
});

// Accordion (only one open at a time)
const accHeaders = document.querySelectorAll('.accordion-header');
accHeaders.forEach(header => {
  header.addEventListener('click', () => {
    const content = header.nextElementSibling;

    // Close all other sections
    document.querySelectorAll('.accordion-content').forEach(c => {
      if (c !== content) {
        c.classList.remove('active');
      }
    });

    // Toggle the clicked one
    content.classList.toggle('active');
  });
});

// Gallery
const images = [
  'images/vulpix1.avif',
  'images/vulpix2.jpg',
  'images/vulpix3.gif',
  'images/vulpix4.jpg',
  'images/vulpix5.gif'
];
let startIndex = 0;
const gallery = document.getElementById('gallery');

function renderGallery() {
  gallery.innerHTML = '';
  for (let i = 0; i < 3; i++) {
    const imgIndex = (startIndex + i) % images.length;
    const img = document.createElement('img');
    img.src = images[imgIndex];
    img.alt = 'Alolan Vulpix';
    gallery.appendChild(img);
  }
}

document.getElementById('prevBtn').addEventListener('click', () => {
  startIndex = (startIndex - 1 + images.length) % images.length;
  renderGallery();
});
document.getElementById('nextBtn').addEventListener('click', () => {
  startIndex = (startIndex + 1) % images.length;
  renderGallery();
});

renderGallery();
