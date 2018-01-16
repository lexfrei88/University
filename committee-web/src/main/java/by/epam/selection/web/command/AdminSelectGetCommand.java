package by.epam.selection.web.command;

import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;
import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.FacultyService;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.web.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lex on 12/16/2017.
 */
public class AdminSelectGetCommand implements Command {

    private static final String USER_LIST_ATTRIBUTE = "userList";
    private static final String FACULTY_ID_PARAMETER = "facultyId";

    private static final View FORWARD_TO_ADMIN_SELECTION_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_ADMIN_SELECTION);

    @SimpleAutowire
    private UserService userService;

    @SimpleAutowire
    private FacultyService facultyService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String parameter = request.getParameter(FACULTY_ID_PARAMETER);
            String facultyIdParameter = WebUtils.requiredNumberParameter(parameter, FACULTY_ID_PARAMETER);
            int facultyId = Integer.parseInt(facultyIdParameter);

            Faculty faculty = facultyService.get(facultyId);
            List<User> userList = userService.getAllSelected(faculty);
            request.setAttribute(USER_LIST_ATTRIBUTE, userList);
        } catch (ServiceException | WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }

        return FORWARD_TO_ADMIN_SELECTION_PAGE;
    }

}
