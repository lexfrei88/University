package by.epam.selection.web.command.ajax;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Certificate;
import by.epam.selection.service.CertificateService;
import by.epam.selection.validation.CertificateValidator;
import by.epam.selection.validation.ConstraintViolation;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CertificatePostAjaxCommandTest {

    private static final View FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE = new View(ActionName.FORWARD, PathConstant.AJAX_CERTIFICATE_POST);

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private AuthenticatedUser authenticatedUser;
    @Mock private CertificateService service;
    @Mock private CertificateValidator validator;
    @Spy private Set<ConstraintViolation> violations = new HashSet<>();

    @InjectMocks
    private CertificatePostAjaxCommand command = new CertificatePostAjaxCommand();

    @Before
    public void setUp() throws Exception {
        when(request.getParameter(anyString()))
                .thenReturn("2")
                .thenReturn("7");
        when(validator.validate(any(Certificate.class))).thenReturn(violations);
    }

    @Test
    public void shouldForwardAjaxResponseJspWithoutViolationsWhenExecute() throws Exception {
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

        verify(violations).iterator();
        Assert.assertEquals(FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE, actual);
    }

}
