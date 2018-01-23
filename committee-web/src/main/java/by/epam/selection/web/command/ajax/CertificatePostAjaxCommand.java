package by.epam.selection.web.command.ajax;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;
import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.CertificateService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.selection.validation.CertificateValidator;
import by.epam.selection.validation.ConstraintViolation;
import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.web.Command;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by lex on 12/29/2017.
 */
public class CertificatePostAjaxCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CertificatePostAjaxCommand.class);

    private static final String CERTIFICATE_ATTRIBUTE = "certificate";

    private static final String CERTIFICATE_ID = "certificateId";
    private static final String SUBJECT_ID_PARAMETER = "subjectId";
    private static final String SCORE_PARAMETER = "score";

    private static final View FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE = new View(ActionName.FORWARD, PathConstant.AJAX_CERTIFICATE_POST);

    @SimpleAutowire
    private CertificateService certificateService;

    @SimpleAutowire
    private CertificateValidator certificateValidator;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[AJAX POST] Set score for certificate.");

        Certificate certificate;
        try {
            String certificateIdParameter = WebUtils.requiredNumberParameter(request.getParameter(CERTIFICATE_ID), CERTIFICATE_ID);
            Long certificateId = Long.parseLong(certificateIdParameter);
            certificateId = certificateId == 0 ? null : certificateId;

            String subjectIdParameter = WebUtils.requiredNumberParameter(request.getParameter(SUBJECT_ID_PARAMETER), SUBJECT_ID_PARAMETER);
            long subjectId = Long.parseLong(subjectIdParameter);
            Subject subject = new Subject(subjectId);

            String scoreParameter = WebUtils.requiredNumberParameter(request.getParameter(SCORE_PARAMETER), SCORE_PARAMETER);
            int score = Integer.parseInt(scoreParameter);

            certificate = new Certificate(certificateId, subject, score);
        } catch (WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }

        Set<ConstraintViolation> violations = certificateValidator.validate(certificate);

        if (violations.isEmpty()) {
            try {
                HttpSession session = request.getSession();
                AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
                Long userId = authenticatedUser.getId();
                Certificate processed = certificateService.saveOrUpdate(certificate, userId);
                request.setAttribute(CERTIFICATE_ATTRIBUTE, processed);
            } catch (ServiceException e) {
                throw new ServletException(e.getMessage(), e);
            }
        } else {
            for (ConstraintViolation violation : violations) {
                request.setAttribute(violation.getKey(), violation.getMessage());
            }
        }
        return FORWARD_TO_AJAX_CERTIFICATE_POST_RESPONSE_PAGE;
    }

}
