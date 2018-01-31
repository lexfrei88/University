<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../fragment/resources.jsp" %>
    <%@ include file="../fragment/i18n.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/faculty.css">
    <title><fmt:message bundle="${i18n}" key="page.select"/></title>
</head>
<body>
<%@ include file="../fragment/header.jsp" %>
<section>
    <form id="facultyForm" action="${pageContext.request.contextPath}/admin/select" method="get">
        <label><span><fmt:message bundle="${i18n}" key="label.choose.faculty"/></span>
            <select onchange="submit()" id="selection" name="facultyId">
                <option id="facultyListHead" hidden selected disabled>Faculties List</option>
                <c:forEach items="${facultyList}" var="f">
                    <option value="${f.id}">${f.facultyName}</option>
                </c:forEach>
            </select>
        </label>
    </form>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td><fmt:message bundle="${i18n}" key="label.first.name"/></td>
            <td><fmt:message bundle="${i18n}" key="label.last.name"/></td>
            <td><fmt:message bundle="${i18n}" key="label.email"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<script>
    var size = document.getElementById("selection").options.length;
    if (size <= 1) {
        document.getElementById("facultyForm").style.display = "none";
    }
</script>
</body>
</html>
