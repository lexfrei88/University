package by.epam.selection.web.command;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminSelectGetCommandTest {

    private static final View FORWARD_TO_ADMIN_SELECTION_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_ADMIN_SELECTION);
    private static final String FACULTY_ID = "3";

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private UserService userService;
    @Mock private FacultyService facultyService;

    @InjectMocks
    private AdminSelectGetCommand command = new AdminSelectGetCommand();

    @Test
    public void shouldForwardSelectionPageWhenExecute() throws Exception {
        when(request.getParameter(anyString())).thenReturn(FACULTY_ID);
        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_ADMIN_SELECTION_PAGE, actual);
    }

    @Test(expected = ServletException.class)
    public void shouldExceptionWhenExecuteAndServiceExceptionOccur() throws Exception {
        when(userService.getAllSelected(any())).thenThrow(ServiceException.class);
        when(request.getParameter(anyString())).thenReturn(FACULTY_ID);

        command.execute(request, response);
    }

}
