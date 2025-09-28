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