window.onload = function identifyFaculty() {
    getCertificatesForFaculty();

    getErrorMsg();
};

function getCertificatesForFaculty() {
    var x = document.getElementById("certificate").getAttribute("data-has-faculty");
    if (x > 0) {
        document.getElementById("certificate").style.display = "block";
        document.getElementById("facultyListHead").style.display = 'none';
        var facultyIdName = 'faculty_' + x;
        var selectedFacultyElement = document.getElementById(facultyIdName);
        selectedFacultyElement.hidden = 'hidden';
        selectedFacultyElement.selected = 'selected';
    }
}

function getErrorMsg () {
    var errorMsgElementArr = document.getElementsByClassName("error-msg");

    for (var i = 0; i < errorMsgElementArr.length; i++) {
        var errorMsgElement = errorMsgElementArr[i];
        var error = errorMsgElement.innerText;
        if (/\S/.test(error)) {
            errorMsgElement.style.display = "block"
        }
    }
}

function ajaxPost() {
    var xhr = new XMLHttpRequest();
    var form = document.getElementById('modal-form');

    var certificateIdValue = form.elements['certificateId'].value;
    var subjectIdValue = form.elements['subjectId'].value;
    var scoreValue = form.elements['score'].value;

    if (certificateIdValue <= 0 || subjectIdValue <= 0 || scoreValue < 0 || scoreValue > 10) {
        return false;
    }

    var body = 'certificateId=' + certificateIdValue +
        '&subjectId=' + subjectIdValue +
        '&score=' + scoreValue;

    var url =  form.elements['url'].value;

    xhr.open('POST', url);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.send(body);

    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return;

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            var arr = xhr.responseText.split('; ');
            if (arr[2] === '' && arr[3] === '') {
                var subId = arr[0];
                document.getElementById('myModal').style.display = 'none';

                var tab = document.getElementById('certificate');
                var rows = tab.getElementsByTagName('tr');
                for (var i = 1; i < rows.length; i++) {
                    if (rows[i].getAttribute('data-sub-id') === subId) {
                        rows[i].getElementsByTagName('td')[1].innerText = arr[1];
                        rows[i].getElementsByTagName('p')[0].setAttribute('data-score', arr[1]);
                        rows[i].getElementsByTagName('p')[0].setAttribute("data-certificate-id", arr[4])
                    }
                }

                var scores = document.getElementsByClassName('score');
                var sum = 0;
                for (i = 0; i < scores.length; i++) {
                    sum += parseInt(scores[i].innerText);
                }

                document.getElementById('sum').innerHTML = sum;
            } else {
                var errors = document.getElementsByClassName('error-msg');
                errors[0].innerText = arr[2];
                errors[1].innerText = arr[3];
                getErrorMsg();
            }
        }

    }
}