<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="fragment/resources.jsp" %>
    <%@ include file="fragment/i18n.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/background.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/section.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css">
    <script async src="${pageContext.request.contextPath}/resources/js/login.js"></script>
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
                <input id="email" type="email" name="email" placeholder="E-mail" pattern=".*[@].*[.].*" required/>
            </label>
            <label>
                <fmt:message bundle="${i18n}" key="label.password"/>
                <input id="password" type="password" name="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required/>
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
