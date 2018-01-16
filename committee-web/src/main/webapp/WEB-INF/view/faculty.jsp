<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/faculty.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/edit.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css">
    <script async src="${pageContext.request.contextPath}/resources/js/modal.js"></script>
    <script async src="${pageContext.request.contextPath}/resources/js/faculty.js"></script>
    <%@include file="fragment/i18n.jsp" %>
    <title><fmt:message bundle="${i18n}" key="page.faculty"/></title>
</head>
<body>
<%@ include file="fragment/header.jsp" %>
<section>

    <form method="post">
        <label>
            <span><fmt:message bundle="${i18n}" key="label.choose.faculty"/></span>
            <select onchange="this.form.submit()" name="facultyId">
                <option id="facultyListHead" hidden selected disabled>Faculties List</option>
                <c:forEach items="${facultyList}" var="f">
                    <option id="faculty_${f.id}" value="${f.id}">${f.facultyName}</option>
                </c:forEach>
            </select>
        </label>
    </form>

    <div id="certificate" data-has-faculty="${facultyId}">
        <table>
            <thead>
            <tr>
                <th><fmt:message bundle="${i18n}" key="label.subject.name"/></th>
                <th><fmt:message bundle="${i18n}" key="label.score"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${certificateList}" var="cert">
                    <tr data-sub-id="${cert.subject.id}">
                        <td>${cert.subject.subjectName}</td>
                        <td class="score">${cert.score}</td>
                        <td>
                            <p data-id="${cert.subject.id}" data-certificate-id="${cert.id}" data-name="${cert.subject.subjectName}"
                               data-score="${cert.score}" class="myBtn"></p>
                        </td>
                        <c:set var="sum" scope="page" value="${sum + cert.score}"/>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <th><fmt:message bundle="${i18n}" key="label.sum"/></th>
                <th id="sum">${sum}</th>
                <th></th>
            </tr>
            </tfoot>
        </table>
    </div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="modal-form">
                <input hidden id="url" name="url" value="${pageContext.request.contextPath}/user/ajax/certificate"/>
                <input hidden id="certificateId" name="certificateId"/>
                <input hidden id="subjectId" name="subjectId"/>
                <div class="form-field">
                    <label><fmt:message bundle="${i18n}" key="label.subject.name"/>:</label>
                    <span id="subjectName"></span>
                </div>
                <div class="form-field">
                    <label><fmt:message bundle="${i18n}" key="label.score"/>:</label>
                    <input id="score" name="score" type="number" min="0" max="10"/>
                    <span class="error-msg">${scoreError}</span>
                    <span class="error-msg">${subjectIdError}</span>
                </div>
                <button type="button" onclick="ajaxPost(this)"><fmt:message bundle="${i18n}" key="button.set.score"/></button>
            </form>
        </div>
    </div>

</section>
</body>
</html>
