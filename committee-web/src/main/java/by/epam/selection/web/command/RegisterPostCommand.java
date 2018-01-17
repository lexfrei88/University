package by.epam.selection.web.command;

import by.epam.selection.entity.User;
import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.selection.validation.ConstraintViolation;
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
import java.util.Set;

/**
 * @author Alex Aksionchik 12/4/2017
 * @version 0.1
 */
public class RegisterPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterPostCommand.class);

    private static final String EMAIL_UNIQUE_INDEX_NAME = "idx_unique_user_email";
    private static final String DUPLICATED_EMAIL_MSG = "Email already exist.";
    private static final String DUPLICATED_EMAIL_ATTRIBUTE = "duplicatedEmail";

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

        String firstName, lastName, email, password;
        try {
            String firstNameParameter = request.getParameter(FIRST_NAME_PARAMETER);
            firstName = WebUtils.requiredNotEmptyParameter(firstNameParameter, FIRST_NAME_PARAMETER);

            String lastNameParameter = request.getParameter(LAST_NAME_PARAMETER);
            lastName = WebUtils.requiredNotEmptyParameter(lastNameParameter, LAST_NAME_PARAMETER);

            String emailParameter = request.getParameter(EMAIL_PARAMETER);
            email = WebUtils.requiredNotEmptyParameter(emailParameter, EMAIL_PARAMETER);

            String passwordParameter = request.getParameter(PASSWORD_PARAMETER);
            password = WebUtils.requiredNotEmptyParameter(passwordParameter, PASSWORD_PARAMETER);
        } catch (WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }

        User user = new User(firstName, lastName, email, password);
        Set<ConstraintViolation> violations = userValidator.validate(user);

        View view;
        if (violations.isEmpty()) {
            try {
                userService.save(user);
                logger.debug("New user has been created.");
                view = REDIRECT_TO_LOGIN_COMMAND;
            } catch (ServiceException e) {
                Throwable root = WebUtils.getExceptionRootCause(e);
                String msg = root.getMessage();
                if (root instanceof SQLIntegrityConstraintViolationException && msg.contains(EMAIL_UNIQUE_INDEX_NAME)) {
                    request.setAttribute(DUPLICATED_EMAIL_ATTRIBUTE, DUPLICATED_EMAIL_MSG);
                    view = FORWARD_TO_REGISTER_PAGE;
                } else {
                    throw new ServletException(e.getMessage(), e);
                }
            }
        } else {
            for (ConstraintViolation violation : violations) {
                request.setAttribute(violation.getKey(), violation.getMessage());
            }
            view = FORWARD_TO_REGISTER_PAGE;
            logger.debug("Wrong input data.");
        }
        return view;
    }

}
