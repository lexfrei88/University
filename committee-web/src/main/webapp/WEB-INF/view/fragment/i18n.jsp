<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : 'ru'}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages/i18n" var="i18n"/>