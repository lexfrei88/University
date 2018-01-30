package by.epam.selection.web.filter.security;

import by.epam.selection.AuthenticatedUser;

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

/**
 * Filter cover the part that is available only for Users that are Authenticated.
 *
 * @author Alex Aksionchik 12/27/2017
 * @version 1.0
 */
public class AuthenticationFilter implements Filter {

    private static final String ERROR_MESSAGE = "Unauthorized access attempt";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME) == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ERROR_MESSAGE);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
