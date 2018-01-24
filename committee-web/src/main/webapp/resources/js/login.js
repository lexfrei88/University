function getErrorMsg() {
    var errorMsgElement = document.getElementsByClassName("error-msg")[0];
    var error = errorMsgElement.getAttribute("data-error-msg");
    if (error > 0) {
        errorMsgElement.style.display = "block"
    }
}

var adminBtn = document.getElementById("admin-credential");
adminBtn.addEventListener("click", setAdminCredential);
var userBtn = document.getElementById("user-credential");
userBtn.addEventListener("click", setUserCredential);

function setAdminCredential() {
    console.log("admin");
    document.getElementById("email").value = "queen@kingslanding.com";
    document.getElementById("password").value = "Youlldiesoon1";
}

function setUserCredential() {
    console.log("user");
    document.getElementById("email").value = "nobody@braavos.com";
    document.getElementById("password").value = "Thewinteriscomming2";
}