<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/background.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css">
    <script async src="${pageContext.request.contextPath}/resources/js/register.js"></script>
    <%@include file="fragment/i18n.jsp" %>
    <title><fmt:message bundle="${i18n}" key="page.register"/></title>
</head>
<body>
<%@ include file="fragment/header.jsp" %>
<section>
    <div class="register-form">
        <form method="post" action="${pageContext.request.contextPath}/register">
            <label for="firstName">
                <fmt:message bundle="${i18n}" key="label.first.name"/>
                <input type="text" placeholder="First Name" pattern="[A-Z].*" name="firstName" id="firstName" required/>
            </label>
            <label for="lastName">
                <fmt:message bundle="${i18n}" key="label.last.name"/>
                <input type="text" placeholder="Last Name" pattern="[A-Z].*" name="lastName" id="lastName" required/>
            </label>
            <label for="email">
                <fmt:message bundle="${i18n}" key="label.email"/>
                <input type="email" placeholder="Email" pattern=".*[@].*[.].*" name="email" id="email" required/>
                <span data-error="${not empty duplicatedEmail}" class="error-msg">
                    ${duplicatedEmail}
                </span>
            </label>
            <label for="password">
                <fmt:message bundle="${i18n}" key="label.password"/>
                <input type="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" id="password" required/>
            </label>
            <input type="submit" value="<fmt:message bundle="${i18n}" key="label.register"/>"/>
        </form>
    </div>
</section>
<div id="tooltip">
    <p>${messages.firstName}</p>
    <p>${messages.lastName}</p>
    <p>${messages.email}</p>
    <p>${messages.password}</p>
    <p>${messages.duplicatedEmail}</p>
</div>
</body>
</html>
