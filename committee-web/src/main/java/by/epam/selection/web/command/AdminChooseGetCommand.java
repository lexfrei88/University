package by.epam.selection.web.command;

import by.epam.selection.entity.Faculty;
import by.epam.selection.service.FacultyService;
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
 * Created by lex on 12/16/2017.
 */
public class AdminChooseGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AdminChooseGetCommand.class);

    private static final String FACULTY_LIST_ATTRIBUTE = "facultyList";

    private static final View FORWARD_TO_ADMIN_SELECTION_PAGE = new View(ActionName.FORWARD, PathConstant.PAGE_ADMIN_SELECTION);

    @SimpleAutowire
    private FacultyService facultyService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[GET] Get list of faculties.");
        try {
            List<Faculty> facultyList = facultyService.getAll();
            request.setAttribute(FACULTY_LIST_ATTRIBUTE, facultyList);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage(), e);
        }
        return FORWARD_TO_ADMIN_SELECTION_PAGE;
    }

}
