<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/faculty.css">
    <%@ include file="../fragment/i18n.jsp" %>
    <title>Error page</title>
</head>
<body>
<%@ include file="../fragment/header.jsp" %>
<section>
    <div style="padding: 2%">
        <h1>Error</h1>
        <p><b>Status code:</b> ${pageContext.errorData.statusCode}</p>
        <p><b>URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
        <p><b>Exception type:</b> ${requestScope['javax.servlet.error.exception_type']}</p>
        <p><b>Exception message:</b> ${requestScope['javax.servlet.error.message']}</p>
    </div>
</section>
</body>
</html>
