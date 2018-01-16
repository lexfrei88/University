package by.epam.selection.web.command;

import by.epam.selection.entity.User;
import by.epam.selection.service.UserService;
import by.epam.selection.validation.ConstraintViolation;
import by.epam.selection.validation.UserValidator;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterPostCommandTest {

    private static final View REDIRECT_TO_LOGIN_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_LOGIN);
    private static final View FORWARD_TO_REGISTER_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_REGISTER);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private UserService service;
    @Mock private UserValidator validator;
    @Spy private Set<ConstraintViolation> violations = new HashSet<>();

    @InjectMocks
    private RegisterPostCommand command = new RegisterPostCommand();

    @Before
    public void setUp() throws Exception {
        when(request.getParameter(anyString()))
                .thenReturn("first_name")
                .thenReturn("last_name")
                .thenReturn("email")
                .thenReturn("password");
        when(validator.validate(any(User.class))).thenReturn(violations);
    }

    @Test
    public void shouldRedirectLoginCommandWhenExecute() throws Exception {
        View actual = command.execute(request, response);

        verify(service).save(any(User.class));
        Assert.assertEquals(REDIRECT_TO_LOGIN_COMMAND, actual);
    }

    @Test
    public void shouldForwardRegisterPageWhenExecute() throws Exception {
        when(violations.isEmpty()).thenReturn(false);

        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_REGISTER_PAGE, actual);
    }

}