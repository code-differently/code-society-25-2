
function showTab(tabId, button) {
  document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
  document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
  button.classList.add('active');
  document.getElementById(tabId).classList.add('active');
}


function toggle(header) {
  let content = header.nextElementSibling;
  content.classList.toggle('open');
}

const images = [
  "images/71YvIyTP6fL._UF1000,1000_QL80_.jpg",
  "images/2911597583.jpg",
  "images/a2539fc2-4cc1-46c5-b847-b74a4307042a.c0aa7cfd8d44dfc19e0c367dcc267ff9.webp",
  "images/homegoing-792_1.jpg"
];

let currentIndex = 0;
const img1 = document.getElementById('image1');
const img2 = document.getElementById('image2');
const img3 = document.getElementById('image3');
const img4 = document.getElementById('image4');

// NOW ADD THESE:

// 1. Function to update the images
function updateImages() {
  img1.src = images[currentIndex];
  img2.src = images[(currentIndex + 1) % images.length];
  img3.src = images[(currentIndex + 2) % images.length];
  img4.src = images[(currentIndex + 3) % images.length];
}

// 2. Get your buttons.
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');

// 3. Add click listeners
nextBtn.addEventListener('click', function() {
  currentIndex = (currentIndex + 1) % images.length;
  updateImages();
});

prevBtn.addEventListener('click', function() {
  currentIndex = (currentIndex - 1 + images.length) % images.length;
  updateImages();
});

// 4. Show the first 3 images when page loads
updateImages();