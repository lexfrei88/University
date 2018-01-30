package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.NotFoundException;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class LoginPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginPostCommand.class);

    private static final String MESSAGES_ERRORS_FILE_PATH = "messages/errors";
    private static final String ERROR_MESSAGES_ATTR = "messages";

    private static final String EMAIL_PARAMETER = "email";
    private static final String WRONG_EMAIL = "wrongEmail";
    private static final String WRONG_EMAIL_BUNDLE_KEY = "wrong.email";

    private static final String PASSWORD_PARAMETER = "password";
    private static final String WRONG_PASSWORD = "wrongPassword";
    private static final String WRONG_PASSWORD_BUNDLE_KEY = "wrong.password";

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);
    private static final View FORWARD_TO_LOGIN_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_LOGIN);


    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[POST] Login command.");

        Locale locale = WebUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_ERRORS_FILE_PATH, locale);
        Map<String, String> errorMessage = new HashMap<>();

        View view;
        try {
            String email = request.getParameter(EMAIL_PARAMETER);
            WebUtils.notEmpty(email, EMAIL_PARAMETER, bundle, errorMessage);

            String password = request.getParameter(PASSWORD_PARAMETER);
            WebUtils.notEmpty(password, PASSWORD_PARAMETER, bundle, errorMessage);

            if (errorMessage.isEmpty()) {
                AuthenticatedUser authenticatedUser = userService.loadUserByUsername(email, password);
                if (authenticatedUser == null) {
                    errorMessage.put(WRONG_PASSWORD, bundle.getString(WRONG_PASSWORD_BUNDLE_KEY));
                    view = FORWARD_TO_LOGIN_PAGE;
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME, authenticatedUser);
                    view = REDIRECT_TO_FACULTY_COMMAND;
                }
            } else {
                view = FORWARD_TO_LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            Throwable root = WebUtils.getExceptionRootCause(e);
            if (root instanceof NotFoundException) {
                errorMessage.put(WRONG_EMAIL, bundle.getString(WRONG_EMAIL_BUNDLE_KEY));
                view = FORWARD_TO_LOGIN_PAGE;
            } else {
                throw new ServletException(e.getMessage(), e);
            }
        }
        request.setAttribute(ERROR_MESSAGES_ATTR, errorMessage);
        return view;
    }

}
