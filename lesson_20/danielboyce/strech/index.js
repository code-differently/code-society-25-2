function openTab(evt,tabNumber) {
    var i,tabContent,tabHeaders;

    tabContent = document.getElementsByClassName("tab-content");
    for(let i =0;i<tabContent.length;i++) {
        tabContent[i].style.display = "none"
    }
    

    tabHeaders = document.getElementsByClassName("tab-header");
    for(let i =0;i<tabHeaders.length;i++) {
        tabHeaders[i].className = tabHeaders[i].className.replace(" active","");
    }
    document.getElementById(tabNumber).style.display = "block";
    evt.currentTarget.className += " active";
}



document.querySelectorAll(".accordion").forEach(accord=> {
    accord.addEventListener("click",(event)=> {
        const accordionButton = event.currentTarget;
        accordionButton.classList.toggle("active");

        const panel = accordionButton.nextElementSibling;
        panel.classList.toggle("show");

    })
});

const images = [
    "lesson_20/danielboyce/images/movie_image.jpeg",
    "lesson_20/danielboyce/images/movie_image1.jpeg",
    "lesson_20/danielboyce/images/movie_image2.jpeg",
    "lesson_20/danielboyce/images/movie_image3.jpeg",
    "lesson_20/danielboyce/images/movie_image4.jpeg",
    "lesson_20/danielboyce/images/movie_image5.jpeg"

];
let i =0
doccument.getElementById("forward")
// document.querySelectorAll("img").forEach(image=>image.src = images[i])





