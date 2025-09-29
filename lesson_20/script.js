document.addEventListener("DOMContentLoaded", () => {
  initTabs(); initAccordion(); initGallery();
});

function initTabs() {
  const root = document.querySelector('[data-component="tabs"]');
  if (!root) return;
  const tabs = Array.from(root.querySelectorAll('.tab'));
  const panels = tabs.map(t => document.getElementById(t.getAttribute('aria-controls')));
  function activate(i) {
    tabs.forEach((t, idx) => {
      const on = idx === i;
      t.setAttribute('aria-selected', String(on));
      t.tabIndex = on ? 0 : -1;
      panels[idx].hidden = !on;
    });
    tabs[i].focus();
  }
  tabs.forEach((t, i) => {
    t.addEventListener('click', () => activate(i));
    t.addEventListener('keydown', (e) => {
      if (e.key === 'ArrowRight') { e.preventDefault(); activate((i + 1) % tabs.length); }
      if (e.key === 'ArrowLeft')  { e.preventDefault(); activate((i - 1 + tabs.length) % tabs.length); }
    });
  });
}

function initAccordion() {
  const root = document.querySelector('[data-component="accordion"]');
  if (!root) return;
  root.querySelectorAll('.accordion-trigger').forEach(btn => {
    const panel = document.getElementById(btn.getAttribute('aria-controls'));
    btn.addEventListener('click', () => {
      const open = btn.getAttribute('aria-expanded') === 'true';
      btn.setAttribute('aria-expanded', String(!open));
      panel.hidden = open;
    });
    btn.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') { e.preventDefault(); btn.click(); }
    });
  });
}

function initGallery() {
  const root = document.querySelector('[data-component="gallery"]');
  if (!root) return;
  const images = [
    "images/photo1.jpg",
    "images/photo2.jpg",
    "images/photo3.jpg",
  ];
  const track = root.querySelector('.gallery-track');
  const prev = root.querySelector('.prev');
  const next = root.querySelector('.next');
  let start = 0;

  function render() {
    track.innerHTML = "";
    const visible = Math.min(3, images.length);
    for (let i = 0; i < visible; i++) {
      const idx = (start + i) % images.length;
      const li = document.createElement('li');
      const img = document.createElement('img');
      img.src = images[idx];
      img.alt = `Photo ${idx + 1}`;
      li.appendChild(img);
      track.appendChild(li);
    }
  }
  function rotate(step) {
    if (images.length === 0) return;
    start = (start + step + images.length) % images.length;
    render();
  }
  prev.addEventListener('click', () => rotate(-1));
  next.addEventListener('click', () => rotate(+1));
  root.addEventListener('keydown', (e) => {
    if (e.key === 'ArrowLeft') rotate(-1);
    if (e.key === 'ArrowRight') rotate(+1);
  });
  render();
}
