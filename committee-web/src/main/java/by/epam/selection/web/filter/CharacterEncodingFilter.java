package by.epam.selection.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Filter that set {@code UTF-8} character encoding for request and response.
 *
 * @author Alex Aksionchik 12/6/2017
 * @version 1.0
 */
public class CharacterEncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(UTF_8);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
