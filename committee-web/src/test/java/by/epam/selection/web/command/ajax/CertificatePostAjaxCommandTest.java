package by.epam.selection.web.command.ajax;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Certificate;
import by.epam.selection.service.CertificateService;
import by.epam.selection.util.WebUtils;
import by.epam.selection.validation.CertificateValidator;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebUtils.class)
@PowerMockIgnore("javax.management.*")
public class CertificatePostAjaxCommandTest {

    private static final View FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE = new View(ActionName.FORWARD, PathConstant.AJAX_CERTIFICATE_POST);

    private static final Locale LOCALE = new Locale("en");

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private AuthenticatedUser authenticatedUser;
    @Mock private CertificateService service;
    @Mock private CertificateValidator validator;
    @Mock private Map<String, String> violations;

    @InjectMocks
    private CertificatePostAjaxCommand command = new CertificatePostAjaxCommand();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(WebUtils.class);
        when(WebUtils.getLocale(request)).thenReturn(LOCALE);

        when(request.getParameter(anyString()))
                .thenReturn("2")
                .thenReturn("7");
        when(validator.validate(any(), any(Certificate.class))).thenReturn(violations);
    }

    @Test
    public void shouldForwardAjaxResponseJspWithoutViolationsWhenExecute() throws Exception {
        when(violations.isEmpty()).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME)).thenReturn(authenticatedUser);

        View actual = command.execute(request, response);

        verify(service).saveOrUpdate(any(Certificate.class), anyLong());
        Assert.assertEquals(FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE, actual);
    }


    @Test
    public void shouldForwardAjaxResponseJspWithViolationsWhenExecute() throws Exception {
        when(violations.isEmpty()).thenReturn(false);

        View actual = command.execute(request, response);

        Assert.assertEquals(FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE, actual);
    }

}
