window.onload = function getErrorMsg() {
    var errorMsgElementArr = document.getElementsByClassName("error-msg");

    for (var i = 0; i < errorMsgElementArr.length; i++) {
        var errorMsgElement = errorMsgElementArr[i];
        var error = errorMsgElement.innerText;
        if (errorMsgElement.getAttribute("data-error") == 'true') {
            errorMsgElement.style.display = "block"
        }
    }
};