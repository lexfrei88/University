window.onload = function getErrorMsg() {
    var errorMsg = document.getElementById("tooltip");
    var text = errorMsg.innerText;
    if (!text || /^\s*$/.test(text)) {
    } else {
        errorMsg.style.display = "block";
    }
};