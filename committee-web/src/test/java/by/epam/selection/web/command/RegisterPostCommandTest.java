package by.epam.selection.web.command;

import by.epam.selection.entity.User;
import by.epam.selection.service.UserService;
import by.epam.selection.util.WebUtils;
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
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebUtils.class)
@PowerMockIgnore("javax.management.*")
public class RegisterPostCommandTest {

    private static final View REDIRECT_TO_LOGIN_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_LOGIN);
    private static final View FORWARD_TO_REGISTER_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_REGISTER);

    private static final Locale LOCALE = new Locale("en");

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private UserService service;
    @Mock private UserValidator validator;
    @Mock private Map<String, String> violations;

    @InjectMocks
    private RegisterPostCommand command = new RegisterPostCommand();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(WebUtils.class);
        when(WebUtils.getLocale(request)).thenReturn(LOCALE);

        when(request.getParameter(anyString()))
                .thenReturn("first_name")
                .thenReturn("last_name")
                .thenReturn("email")
                .thenReturn("password");
        when(validator.validate(any(), any(User.class))).thenReturn(violations);
    }

    @Test
    public void shouldRedirectLoginCommandWhenExecute() throws Exception {
        when(violations.isEmpty()).thenReturn(true);
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