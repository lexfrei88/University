package by.epam.selection.web.filter.security;

import by.epam.selection.AuthenticatedUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    private static final Object DUMMY_OBJECT = new Object();
    private static final String UNAUTHORIZED_ACCESS_ATTEMPT_MESSAGE = "Unauthorized access attempt";

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpSession session;

    @Test
    public void shouldSendErrorWhenDoFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        when(request.getSession(false)).thenReturn(null);

        authenticationFilter.doFilter(request, response, chain);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_ACCESS_ATTEMPT_MESSAGE);
    }

    @Test
    public void shouldDoFilterWhenDoFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(DUMMY_OBJECT);

        authenticationFilter.doFilter(request, response, chain);
        verify(chain).doFilter(request, response);
    }

}
