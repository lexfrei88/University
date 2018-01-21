package by.epam.selection.web.filter.security;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the base implementation of authority filter.
 * <p>
 * In general for filtering extend this class with no overriding or custom code and
 * do other job in {@code web.xml} file. There you must map you filter for url you wish
 * to protect and add for it init params with any name, but the value must
 * be equals to roles names from enum class {@link Role} you wish to have access
 * for specified url.
 *
 * @author Alex Aksionchik 12/27/2017
 * @version 1.0
 */
public abstract class AbstractAuthorizationFilter implements Filter {

    private Logger logger = LogManager.getLogger(this.getClass());

    private Set<Role> roles = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String paramValue = filterConfig.getInitParameter(name);
            try {
                Role role = Role.valueOf(paramValue);
                roles.add(role);
            } catch (IllegalArgumentException e) {
                logger.warn("No constant with the name {} in Role enum class.", paramValue);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
        if (authenticatedUser.isUserInRole(roles)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }

}
