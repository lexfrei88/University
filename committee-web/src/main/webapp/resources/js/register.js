window.onload = function getErrorMsg() {
    var errorMsg = document.getElementById("tooltip");
    var text = errorMsg.innerText;
    if (/\S/.test(text)) {
        errorMsg.style.display = "block";
    }
};