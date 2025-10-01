// Tabs
const tabButtons = document.querySelectorAll('.tab-btn');
const tabContents = document.querySelectorAll('.tab-content');
tabButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        tabButtons.forEach(b => b.classList.remove('active'));
        tabContents.forEach(tc => tc.classList.remove('active'));
        btn.classList.add('active');
        document.getElementById(btn.dataset.tab).classList.add('active');
    });
});

// Accordion (multiple selectable)
const accHeaders = document.querySelectorAll('.accordion-header');
accHeaders.forEach((header) => {
    header.addEventListener('click', () => {
        const isActive = header.classList.contains('active');
        if (isActive) {
            header.classList.remove('active');
            header.querySelector('.accordion-icon').textContent = 'ᐅ';
            header.nextElementSibling.style.display = 'none';
        } else {
            header.classList.add('active');
            header.querySelector('.accordion-icon').textContent = '▼';
            header.nextElementSibling.style.display = 'block';
        }
    });
});

// Gallery (use local images)
const images = [
    'images/SpaceImage1.jpeg',
    'images/SpaceImage2.jpeg',
    'images/spaceImage3.jpeg',
    'images/spaceImage4.jpeg',
    'images/spaceImage5.jpeg',
    'images/spaceImage6.jpeg'
];
let startIdx = 0;
const galleryPhotos = document.getElementById('gallery-photos');
function renderGallery() {
    galleryPhotos.innerHTML = '';
    for (let i = 0; i < 3; i++) {
        const imgIdx = (startIdx + i) % images.length;
        const img = document.createElement('img');
        img.src = images[imgIdx];
        img.alt = `Gallery image ${imgIdx + 1}`;
        galleryPhotos.appendChild(img);
    }
}
document.getElementById('prev-btn').addEventListener('click', () => {
    startIdx = (startIdx - 1 + images.length) % images.length;
    renderGallery();
});
document.getElementById('next-btn').addEventListener('click', () => {
    startIdx = (startIdx + 1) % images.length;
    renderGallery();
});
renderGallery();
