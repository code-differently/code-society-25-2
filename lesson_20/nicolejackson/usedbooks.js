alert("JavaScript is loaded!");

// Tabs
function showTab(tabId, button) {
  document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
  document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
  button.classList.add('active');
  document.getElementById(tabId).classList.add('active');
}

// Accordion
function toggle(header) {
  let content = header.nextElementSibling;
  content.classList.toggle('open');
}

// Gallery Slider
let pos = 0;
function slide(dir) {
  let slides = document.getElementById('slides');
  let slideCount = slides.children.length;
  pos = Math.max(0, Math.min(slideCount - 1, pos + dir));
  let percentage = pos * 100;
  slides.style.transform = `translateX(-${percentage}%)`;
}
