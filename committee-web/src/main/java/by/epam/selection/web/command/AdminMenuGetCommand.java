package by.epam.selection.web.command;

import by.epam.selection.entity.User;
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
import java.util.List;

/**
 * Created by lex on 12/9/2017.
 */
public class AdminMenuGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AdminMenuGetCommand.class);

    private static final String USER_LIST_ATTRIBUTE = "userList";

    private static final View FORWARD_TO_ADMIN_MENU_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_ADMIN_MENU);

    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[GET] Get list of unapproved users for admin.");
        try {
            List<User> userList = userService.getAllUnapproved();
            request.setAttribute(USER_LIST_ATTRIBUTE, userList);
            return FORWARD_TO_ADMIN_MENU_PAGE;
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

}
