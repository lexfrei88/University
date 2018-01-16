package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.User;
import by.epam.selection.service.CertificateService;
import by.epam.selection.service.FacultyService;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.junit.Assert;
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
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class FacultyGetCommandTest {

    private static final View FORWARD_TO_FACULTY_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_FACULTY);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private AuthenticatedUser authenticatedUser;
    @Mock private UserService userService;
    @Mock private User user;
    @Mock private FacultyService facultyService;
    @Mock private CertificateService certificateService;

    @InjectMocks
    private FacultyGetCommand command = new FacultyGetCommand();

    @Test
    public void shouldForwardFacultyPageWhenExecute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(authenticatedUser);
        when(userService.get(anyLong())).thenReturn(user);

        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_FACULTY_PAGE, actual);
    }

    @Test(expected = ServletException.class)
    public void shouldExceptionWhenExecuteAndServiceExceptionOccur() throws Exception {
        when(facultyService.getAll()).thenThrow(ServiceException.class);

        command.execute(request, response);
    }

}
