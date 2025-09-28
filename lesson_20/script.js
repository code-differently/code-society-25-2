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
  'https://static0.thegamerimages.com/wordpress/wp-content/uploads/2020/08/Snowy1.jpg?q=50&fit=crop&w=825&dpr=1.5',
  'https://i.pinimg.com/736x/e7/24/f6/e724f64f6d43679caa5365a0fda2abb3.jpg',
  'https://64.media.tumblr.com/eef490213b5abeef75f360fb20c512d1/ae7f4a36cc63afbc-34/s250x400/fc6e299d1b0b3919f1144ed81431282c66b1148b.gif',
  'https://i.pinimg.com/736x/d0/71/d2/d071d2cb8c8d9538151f86680b624d21.jpg',
  'https://pa1.aminoapps.com/6665/1fa7477dbe2e18b9159e8c5f6f56f195b5a46db3_hq.gif'
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
