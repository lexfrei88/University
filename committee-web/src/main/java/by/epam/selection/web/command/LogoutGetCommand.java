package by.epam.selection.web.command;

import by.epam.selection.AuthenticatedUser;
import by.epam.study.web.view.PathConstant;
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
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class LogoutGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutGetCommand.class);

    private static final View REDIRECT_TO_LOGIN_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_LOGIN);

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[GET] Logout command.");
        HttpSession session = request.getSession();
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
        if (authenticatedUser != null) {
            session.invalidate();
        }
        return REDIRECT_TO_LOGIN_COMMAND;
    }

}
