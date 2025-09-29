//Tab
function openTab(event, tabName){
  //Hide tab content
  const tabContent = document.getElementsByClassName("tabcontent");
  for(let i=0;i<tabContent.length;i++){
    tabContent[i].style.display = "none";
  }

  //Remove active class from tab buttons
  const tabLinks = document.getElementsByClassName("tablinks");
  for(let i=0;i<tabLinks.length;i++){
    tabLinks[i].classList.remove("active");
  }


  document.getElementById(tabName).style.display = "block";

  event.currentTarget.classList.add("active");
}

//Accordion
const accord = document.getElementsByClassName("accordion");
for (let i = 0; i < accord.length; i++) {
  accord[i].addEventListener("click", (event) => {
    const accordionButton = event.currentTarget;
    accordionButton.classList.toggle("active");
   
    const panel = accordionButton.nextElementSibling;
    panel.classList.toggle("show");
  });
}

//Gallery
const imagePaths = [
  'images/flower1.jpeg',
  'images/flower2.jpeg',
  'images/painting.jpeg'
];

const gallery = document.querySelector('.gallery');
let startIndex = 0;
const visibleCount = 1;

function updateGallery(){
  gallery.innerHTML ='';
  for (let i=0;i<visibleCount;i++){
    const imgIndex = (startIndex + i) % imagePaths.length;
    const img = document.createElement('img');
    img.src = imagePaths[imgIndex];
    img.alt = `Photo ${imgIndex + 1}`;
    gallery.appendChild(img);
  }
}

document.getElementById('nextButton').addEventListener('click', () => {
  startIndex = (startIndex + visibleCount) % imagePaths.length;
  updateGallery();
});

document.getElementById('prevButton').addEventListener('click', () => {
  startIndex = (startIndex - visibleCount + imagePaths.length) % imagePaths.length;
  updateGallery();
});


updateGallery();
