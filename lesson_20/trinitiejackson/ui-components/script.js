const tabButtons = document.querySelectorAll('.tab-buttons button');
const tabContents = document.querySelectorAll('.tab-content');

tabButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    tabButtons.forEach(b => b.classList.remove('active'));
    tabContents.forEach(c => c.classList.remove('active'));

    btn.classList.add('active');
    document.getElementById(btn.dataset.tab).classList.add('active');
  });
});


const accordionHeaders = document.querySelectorAll('.accordion-header');
accordionHeaders.forEach(header => {
  header.addEventListener('click', () => {
    const content = header.nextElementSibling;
     header.classList.toggle('active');  
    content.classList.toggle('active');
  });
});


const images = [
  "images/meme_1.jpg",
  "images/meme_2.jpg",
  "images/meme_3.jpg",
  "images/meme_4.jpg",
  "images/meme_5.jpg",
  "images/meme_6.jpg"
];

let currentIndex = 0;
const galleryContainer = document.getElementById('galleryContainer');

function renderGallery() {
  galleryContainer.innerHTML = '';
  for (let i = 0; i < 3; i++) {
    const imgIndex = (currentIndex + i) % images.length;
    const img = document.createElement('img');
    img.src = images[imgIndex];
    img.alt = `Gallery image ${imgIndex + 1}`;
    galleryContainer.appendChild(img);
  }
}

document.getElementById('prevBtn').addEventListener('click', () => {
  currentIndex = (currentIndex - 1 + images.length) % images.length;
  renderGallery();
});

document.getElementById('nextBtn').addEventListener('click', () => {
  currentIndex = (currentIndex + 1) % images.length;
  renderGallery();
});

renderGallery();
