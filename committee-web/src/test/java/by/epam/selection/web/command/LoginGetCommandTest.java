package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginGetCommandTest {

    private static final View FORWARD_TO_LOGIN_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_LOGIN);
    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private AuthenticatedUser authenticatedUser;

    private LoginGetCommand command = new LoginGetCommand();

    @Test
    public void shouldRedirectFacultyCommandWhenExecuteWithAuthenticatedUser() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(authenticatedUser);

        View actual = command.execute(request, response);

        Assert.assertEquals(REDIRECT_TO_FACULTY_COMMAND, actual);
    }

    @Test
    public void shouldForwardLoginPageWhenExecuteWithoutSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_LOGIN_PAGE, actual);
    }

}