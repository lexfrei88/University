package by.epam.selection.web.command;

import by.epam.selection.entity.User;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.selection.validation.UserValidator;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Alex Aksionchik 12/4/2017
 * @version 0.1
 */
public class RegisterPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterPostCommand.class);

    private static final String MESSAGES_ERRORS_FILE_PATH = "messages/errors";
    private static final String ERROR_MESSAGES_ATTR = "messages";

    private static final String EMAIL_UNIQUE_INDEX_NAME = "idx_unique_user_email";
    private static final String DUPLICATED_EMAIL_ATTRIBUTE = "duplicatedEmail";
    private static final String DUPLICATED_EMAIL_BUNDLE_KEY = "duplicated.email";

    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";

    private static final View FORWARD_TO_REGISTER_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_REGISTER);
    private static final View REDIRECT_TO_LOGIN_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_LOGIN);

    @SimpleAutowire
    private UserService userService;

    @SimpleAutowire
    private UserValidator userValidator;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[POST] Register new user.");

        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String lastName = request.getParameter(LAST_NAME_PARAMETER);
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        Locale locale = WebUtils.getLocale(request);
        User user = new User(firstName, lastName, email, password);
        Map<String, String> errorMessage = userValidator.validate(locale, user);

        View view;
        if (errorMessage.isEmpty()) {
            try {
                userService.save(user);
                logger.debug("New user has been created.");
                view = REDIRECT_TO_LOGIN_COMMAND;
            } catch (ServiceException e) {
                Throwable root = WebUtils.getExceptionRootCause(e);
                String msg = root.getMessage();
                if (root instanceof SQLIntegrityConstraintViolationException && msg.contains(EMAIL_UNIQUE_INDEX_NAME)) {
                    ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_ERRORS_FILE_PATH, locale);
                    errorMessage.put(DUPLICATED_EMAIL_ATTRIBUTE, bundle.getString(DUPLICATED_EMAIL_BUNDLE_KEY));
                    view = FORWARD_TO_REGISTER_PAGE;
                } else {
                    throw new ServletException(e.getMessage(), e);
                }
            }
        } else {
            logger.debug("Wrong input data.");
            view = FORWARD_TO_REGISTER_PAGE;
        }
        request.setAttribute(ERROR_MESSAGES_ATTR, errorMessage);
        return view;
    }

}
