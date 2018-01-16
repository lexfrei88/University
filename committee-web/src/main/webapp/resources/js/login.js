function getErrorMsg() {
    var errorMsgElement = document.getElementsByClassName("error-msg")[0];
    var error = errorMsgElement.getAttribute("data-error-msg");
    if (error > 0) {
        errorMsgElement.style.display = "block"
    }
}