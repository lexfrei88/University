function getErrorMsg() {
    var errorMsg = document.getElementById("tooltip");
    var text = errorMsg.innerText;
    if (/\S/.test(text)) {
        errorMsg.style.display = "block";
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