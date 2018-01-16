package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class FacultyPostCommandTest {

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);
    private static final long AUTHENTICATED_USER_ID = 2L;
    private static final String FACULTY_ID = "3";

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private UserService service;
    @Mock private HttpSession session;
    @Mock private AuthenticatedUser authenticatedUser;

    @InjectMocks
    private FacultyPostCommand command = new FacultyPostCommand();

    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(authenticatedUser);
        when(authenticatedUser.getId()).thenReturn(AUTHENTICATED_USER_ID);
        when(request.getParameter(anyString())).thenReturn(FACULTY_ID);
    }

    @Test
    public void shouldRedirectFacultyCommandWhenExecute() throws Exception {
        View actual = command.execute(request, response);

        verify(service).updateFaculty(Long.parseLong(FACULTY_ID), AUTHENTICATED_USER_ID);
        Assert.assertEquals(REDIRECT_TO_FACULTY_COMMAND, actual);
    }

    @Test(expected = ServletException.class)
    public void shouldExceptionWhenExecuteAndServiceExceptionOccur() throws Exception {
        when(service.updateFaculty(anyLong(), anyLong())).thenThrow(ServiceException.class);

        command.execute(request, response);
    }

}
