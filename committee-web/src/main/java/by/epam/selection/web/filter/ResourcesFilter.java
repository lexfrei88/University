package by.epam.selection.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Alex Aksionchik 12/7/2017
 * @version 0.1
 */
public class ResourcesFilter implements Filter {

    private static final String DEFAULT_DISPATCHER_NAME = "default";

    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher = filterConfig.getServletContext().getNamedDispatcher(DEFAULT_DISPATCHER_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        defaultRequestDispatcher.forward(request, response);
    }

    @Override
    public void destroy() {

    }

}
