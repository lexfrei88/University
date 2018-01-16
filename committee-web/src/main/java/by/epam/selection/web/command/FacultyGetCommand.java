package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;
import by.epam.selection.service.CertificateService;
import by.epam.selection.service.FacultyService;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.web.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Aksionchik 12/9/2017
 * @version 0.1
 */
public class FacultyGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FacultyGetCommand.class);

    private static final String FACULTY_LIST_ATTRIBUTE = "facultyList";
    private static final String CERTIFICATE_LIST_ATTRIBUTE = "certificateList";
    private static final String FACULTY_ID_ATTRIBUTE = "facultyId";

    private static final View FORWARD_TO_FACULTY_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_FACULTY);

    @SimpleAutowire
    private FacultyService facultyService;

    @SimpleAutowire
    private CertificateService certificateService;

    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[GET] Faculties and certificates.");
        try {
            List<Faculty> facultyList = facultyService.getAll();
            request.setAttribute(FACULTY_LIST_ATTRIBUTE, facultyList);

            HttpSession session = request.getSession();
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
            long userId = authenticatedUser.getId();
            User user = userService.get(userId);

            Faculty faculty = user.getFaculty();
            if (faculty != null) {
                List<Certificate> certificateList = certificateService.getAllForUser(userId);
                request.setAttribute(CERTIFICATE_LIST_ATTRIBUTE, certificateList);
                request.setAttribute(FACULTY_ID_ATTRIBUTE, faculty.getId());
            }
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage(), e);
        }
        return FORWARD_TO_FACULTY_PAGE;
    }

}
