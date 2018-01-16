package by.epam.selection.web.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourcesFilterTest {

    private static final String DEFAULT_DISPATCHER_NAME = "default";

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher defaultRequestDispatcher;
    @Mock
    private FilterChain chain;

    @InjectMocks
    private ResourcesFilter filter = new ResourcesFilter();

    @Test
    public void init() throws Exception {
        when(filterConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getNamedDispatcher(DEFAULT_DISPATCHER_NAME)).thenReturn(defaultRequestDispatcher);

        filter.init(filterConfig);
        verify(filterConfig).getServletContext();
        verify(servletContext).getNamedDispatcher(DEFAULT_DISPATCHER_NAME);
    }

    @Test
    public void doFilter() throws Exception {
        filter.doFilter(request, response, chain);
        verify(defaultRequestDispatcher).forward(request, response);
    }

}