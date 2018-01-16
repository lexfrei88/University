// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementsByClassName("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
var btnClick = function() {
    var certificateId = this.getAttribute("data-certificate-id");
    document.getElementById("certificateId").setAttribute("value", certificateId);
    
    var subjectId = this.getAttribute("data-id");
    document.getElementById("subjectId").setAttribute("value", subjectId);

    var score = this.getAttribute("data-score");
    document.getElementById("score").setAttribute("value", score);
    document.getElementById("score").value = score;

    var subjectName = this.getAttribute("data-name");
    var subjectNameElement = document.getElementById("subjectName");
    subjectNameElement.setAttribute("value", subjectName);
    subjectNameElement.innerHTML = subjectName;

    var errors = document.getElementById('myModal').getElementsByClassName("error-msg");
    for (var i = 0; i < errors.length; i++) {
        errors[i].innerHTML = '';
    }

    modal.style.display = "block";
};

for (var i = 0; i < btn.length; i++) {
    btn[i].addEventListener('click', btnClick);
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};