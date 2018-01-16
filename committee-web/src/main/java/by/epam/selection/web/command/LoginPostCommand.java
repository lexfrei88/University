package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
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

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class LoginPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginPostCommand.class);

    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CREDENTIAL_MISTAKE_ATTRIBUTE = "mistake";
    private static final String MISTAKE_VALUE = "1";

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);
    private static final View FORWARD_TO_LOGIN_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_LOGIN);

    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[POST] Login command.");

        View view = REDIRECT_TO_FACULTY_COMMAND;
        try {
            String emailParameter = request.getParameter(EMAIL_PARAMETER);
            String email = WebUtils.requiredNotEmptyParameter(emailParameter, EMAIL_PARAMETER);

            String passwordParameter = request.getParameter(PASSWORD_PARAMETER);
            String password = WebUtils.requiredNotEmptyParameter(passwordParameter, PASSWORD_PARAMETER);

            AuthenticatedUser authenticatedUser = userService.loadUserByUsername(email, password);
            if (authenticatedUser == null) {
                request.setAttribute(CREDENTIAL_MISTAKE_ATTRIBUTE, MISTAKE_VALUE);
                view = FORWARD_TO_LOGIN_PAGE;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME, authenticatedUser);
            }
        } catch (ServiceException | WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }
        return view;
    }

}
