<%@ taglib prefix="security" uri="/WEB-INF/tld/security.tld" %>
<header>
    <ul>
        <input type="checkbox" id="collapse"/>
        <label for="collapse">
            <div></div>
            <div></div>
            <div></div>
        </label>
        <security:guest>
            <li>
                <a href="${pageContext.request.contextPath}/login">
                    <fmt:message bundle="${i18n}" key="menu.login"/>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/register">
                    <fmt:message bundle="${i18n}" key="menu.register"/>
                </a>
            </li>
        </security:guest>

        <security:authenticate>
            <security:authorized role="ROLE_USER">
                <li>
                    <a href="${pageContext.request.contextPath}/user/faculty">
                        <fmt:message bundle="${i18n}" key="menu.faculty"/>
                    </a>
                </li>
            </security:authorized>
            <security:authorized role="ROLE_ADMIN">
                <li class="dropdown left">
                    <span class="dropbtn">
                        <fmt:message bundle="${i18n}" key="menu.admin"/>
                    </span>
                    <div class="dropdown-content left">
                        <a href="${pageContext.request.contextPath}/admin/approve">
                            <fmt:message bundle="${i18n}" key="menu.approve"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/choose">
                            <fmt:message bundle="${i18n}" key="menu.select"/>
                        </a>
                    </div>
                </li>
            </security:authorized>

            <li>
                <a href="${pageContext.request.contextPath}/logout">
                    <fmt:message bundle="${i18n}" key="menu.logout"/>
                </a>
            </li>
        </security:authenticate>

        <li class="dropdown lang">
            <span class="dropbtn">${lang.toUpperCase()}</span>
            <div class="dropdown-content">
                <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">RU</a>
                <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">EN</a>
            </div>
        </li>
    </ul>
</header>
