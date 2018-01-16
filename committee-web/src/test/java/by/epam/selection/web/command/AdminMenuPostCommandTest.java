package by.epam.selection.web.command;

import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.ActionName;
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
public class AdminMenuPostCommandTest {

    private static final View REDIRECT_TO_ADMIN_APPROVE_STATUS_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_ADMIN_APPROVE);
    private static final String[] ARRAY_OF_USERS_ID = {"1", "5"};

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private UserService service;

    @InjectMocks
    private AdminMenuPostCommand command = new AdminMenuPostCommand();

    @Test
    public void shouldRedirectToApproveCommandWhenExecute() throws Exception {
        when(request.getParameterValues(anyString())).thenReturn(ARRAY_OF_USERS_ID);

        View actual = command.execute(request, response);

        Assert.assertEquals(REDIRECT_TO_ADMIN_APPROVE_STATUS_COMMAND, actual);
    }

    @Test(expected = ServletException.class)
    public void shouldExceptionWhenExecuteAndServiceExceptionOccur() throws Exception {
        when(request.getParameterValues(anyString())).thenReturn(ARRAY_OF_USERS_ID);
        when(service.updateApproveStatus((long[])any())).thenThrow(ServiceException.class);

        command.execute(request, response);
    }

}
