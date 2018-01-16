package by.epam.selection.web.command;

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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminMenuGetCommandTest {

    private static final View FORWARD_TO_ADMIN_MENU_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_ADMIN_MENU);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private UserService service;

    @InjectMocks
    private AdminMenuGetCommand command = new AdminMenuGetCommand();

    @Test
    public void shouldForwardMenuPageWhenExecute() throws Exception {
        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_ADMIN_MENU_PAGE, actual);
    }


    @Test(expected = ServletException.class)
    public void shouldExceptionWhenExecuteAndServiceExceptionOccur() throws Exception {
        Mockito.when(service.getAllUnapproved()).thenThrow(ServiceException.class);

        command.execute(request, response);
    }

}