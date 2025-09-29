function openBron(evt, bronCity) {
  var i, tabcontent, tablinks;

  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(bronCity).style.display = "block";
  evt.currentTarget.className += " active";
}

const gallery = document.querySelector('.gallery');
const back = document.querySelector('.prev');
const forward = document.querySelector('.next');

function updateVisibleImages() {
  const pics = gallery.querySelectorAll('.bronPics');
  pics.forEach((pic, idx) => {
    pic.classList.remove('visible');
    if (idx < 3) {
      pic.classList.add('visible');
    }
  });
}

forward.addEventListener('click', () => {
  const first = gallery.querySelector('.bronPics');
  gallery.appendChild(first);
  updateVisibleImages();
});

back.addEventListener('click', () => {
  const pics = gallery.querySelectorAll('.bronPics');
  const last = pics[pics.length - 1];
  gallery.insertBefore(last, gallery.firstElementChild);
  updateVisibleImages();
});

updateVisibleImages();
