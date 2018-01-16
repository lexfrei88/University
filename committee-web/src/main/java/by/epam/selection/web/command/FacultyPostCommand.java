package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.study.web.view.PathConstant;
import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.web.Command;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Alex Aksionchik 12/9/2017
 * @version 0.1
 */
public class FacultyPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FacultyPostCommand.class);

    private static final String FACULTY_ID_PARAMETER = "facultyId";

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);

    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[POST] Choose faculty.");

        try {
            HttpSession session = request.getSession();
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
            long userId = authenticatedUser.getId();

            String parameter = request.getParameter(FACULTY_ID_PARAMETER);
            String facultyIdParameter = WebUtils.requiredNumberParameter(parameter, FACULTY_ID_PARAMETER);
            long facultyId = Long.parseLong(facultyIdParameter);

            userService.updateFaculty(facultyId, userId);
        } catch (ServiceException | WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }
        return REDIRECT_TO_FACULTY_COMMAND;
    }

}
