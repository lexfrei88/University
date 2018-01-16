package by.epam.selection.web.filter.security;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Role;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminAuthorizationFilterTest {

    private static final String ROLE_ADMIN_PARAMETER_NAME = "admin";
    private static final String ROLE_ADMIN_PARAMETER_VALUE = "ROLE_ADMIN";
    private static final String ROLE_ADMIN_INVALID_PARAM_VALUE = "invalid_value";

    @Mock
    private FilterConfig filterConfig;
    @Mock
    private Enumeration<String> initParameterNames;
    @Mock
    private Logger logger;
    @Spy
    private Set<Role> rolesSpy = new HashSet<>();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private FilterChain chain;
    @Mock
    private AuthenticatedUser authenticatedUser;

    @InjectMocks
    private AdminAuthorizationFilter filter = new AdminAuthorizationFilter();

    @Before
    public void setUp() throws Exception {
        when(filterConfig.getInitParameterNames()).thenReturn(initParameterNames);
        when(initParameterNames.hasMoreElements())
                .thenReturn(true)
                .thenReturn(false);
        when(initParameterNames.nextElement()).thenReturn(ROLE_ADMIN_PARAMETER_NAME);

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(authenticatedUser);

    }

    @Test
    public void shouldInitCorrectWhenInit() throws Exception {
        when(filterConfig.getInitParameter(ROLE_ADMIN_PARAMETER_NAME)).thenReturn(ROLE_ADMIN_PARAMETER_VALUE);

        filter.init(filterConfig);
        verify(rolesSpy).add(any());

        Assert.assertEquals(1, rolesSpy.size());
    }

    @Test
    public void shouldLogExceptionWhenInitWithInvalidData() throws Exception {
        when(filterConfig.getInitParameter(ROLE_ADMIN_PARAMETER_NAME)).thenReturn(ROLE_ADMIN_INVALID_PARAM_VALUE);

        filter.init(filterConfig);
        verify(rolesSpy, never()).add(any());
        verify(logger).warn(anyString(), anyString());

        Assert.assertEquals(0, rolesSpy.size());
    }

    @Test
    public void shouldChainDoFilterWhenDoFilter() throws Exception {
        when(authenticatedUser.isUserInRole(rolesSpy)).thenReturn(true);

        filter.doFilter(request, response, chain);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldSendErrorWhenDoFilter() throws Exception {
        when(authenticatedUser.isUserInRole(rolesSpy)).thenReturn(false);

        filter.doFilter(request, response, chain);
        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

}
