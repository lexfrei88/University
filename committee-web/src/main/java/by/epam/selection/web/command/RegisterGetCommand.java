package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.study.web.Command;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Alex Aksionchik 12/4/2017
 * @version 0.1
 */
public class RegisterGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterGetCommand.class);

    private static final View REDIRECT_TO_FACULTY_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_FACULTY);
    private static final View FORWARD_TO_REGISTER_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_REGISTER);

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[GET] Register page.");

        boolean isAuthenticated = false;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME) != null) {
            isAuthenticated = true;
        }
        return isAuthenticated ? REDIRECT_TO_FACULTY_COMMAND : FORWARD_TO_REGISTER_PAGE;
    }

}
