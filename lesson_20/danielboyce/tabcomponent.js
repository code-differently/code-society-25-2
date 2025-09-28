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