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
public class RegisterGetCommandTest {

    private static final Object DUMMY_OBJECT = new Object();

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);
    private static final View FORWARD_TO_REGISTER_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_REGISTER);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;

    private RegisterGetCommand command = new RegisterGetCommand();

    @Test
    public void shouldRedirectFacultyCommandWhenExecuteWithAuthenticatedUser() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(DUMMY_OBJECT);

        View actual = command.execute(request, response);

        Assert.assertEquals(REDIRECT_TO_FACULTY_COMMAND, actual);
    }

    @Test
    public void shouldForwardRegisterPageWhenExecuteWithoutSession() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_REGISTER_PAGE, actual);
    }

}