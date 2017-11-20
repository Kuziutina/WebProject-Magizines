function openTab(event, tabName) {
    var i, tabcontents, tablinks;

    tabcontents = document.getElementsByClassName("tabcontent");
    for (i=0; i < tabcontents.length; i++) {
        tabcontents[i].style.display="none";
    }

    tablinks = document.getElementsByClassName("tablink");
    for (i=0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    event.currentTarget.className += " active";
}

document.getElementById("onDefault").click();
