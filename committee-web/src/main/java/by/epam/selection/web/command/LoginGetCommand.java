package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import by.epam.study.web.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class LoginGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginGetCommand.class);

    private static final View FORWARD_TO_LOGIN_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_LOGIN);
    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[GET] Login command.");

        boolean isAuthenticated = false;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME) != null) {
            isAuthenticated = true;
        }
        return isAuthenticated ? REDIRECT_TO_FACULTY_COMMAND : FORWARD_TO_LOGIN_PAGE;
    }

}
