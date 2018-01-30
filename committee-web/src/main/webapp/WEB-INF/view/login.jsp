<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/background.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/section.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css">
    <script async src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    <%@ include file="fragment/i18n.jsp" %>
    <title><fmt:message bundle="${i18n}" key="page.login"/></title>
</head>

<body onload="getErrorMsg()">
<%@ include file="fragment/header.jsp" %>

<section>
    <div class="login-form">
        <h1>
            <fmt:message bundle="${i18n}" key="message.welcome"/>
        </h1>
        <p>
            <fmt:message bundle="${i18n}" key="message.title"/>
        </p>
        <form name="login" action="${pageContext.request.contextPath}/login" method="post">
            <label>
                <fmt:message bundle="${i18n}" key="label.login"/>
                <input id="email" type="email" name="email" placeholder="E-mail" pattern=".*[@].*[.].*"/>
            </label>
            <label>
                <fmt:message bundle="${i18n}" key="label.password"/>
                <input id="password" type="password" name="password" placeholder="Password"/>
            </label>
            <input type="submit" value="Login">
            <input id="admin-credential" type="button" value="Admin Credential"/>
            <input id="user-credential" type="button" value="User Credential"/>
        </form>
    </div>
</section>
<div id="tooltip">
    <p>${messages.email}</p>
    <p>${messages.password}</p>
    <p>${messages.wrongPassword}</p>
    <p>${messages.wrongEmail}</p>
</div>
</body>

</html>
