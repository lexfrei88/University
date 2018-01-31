<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../fragment/resources.jsp" %>
    <%@ include file="../fragment/i18n.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
    <title><fmt:message bundle="${i18n}" key="page.approve"/></title>
</head>
<body>
<%@ include file="../fragment/header.jsp" %>
<section>
<form action="${pageContext.request.contextPath}/admin/approve" method="post">
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td><fmt:message bundle="${i18n}" key="label.first.name"/></td>
            <td><fmt:message bundle="${i18n}" key="label.last.name"/></td>
            <td><fmt:message bundle="${i18n}" key="label.email"/></td>
            <td><fmt:message bundle="${i18n}" key="label.approve.status"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td><input type="checkbox" name="approve" value="${user.id}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="<fmt:message bundle="${i18n}" key="button.submit"/>">
</form>
</section>
</body>
</html>
