package by.epam.selection.web.command;

import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.UserService;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.util.WebUtils;
import by.epam.study.web.view.PathConstant;
import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.web.Command;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by lex on 12/10/2017.
 */
public class AdminMenuPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AdminMenuPostCommand.class);

    private static final String APPROVE_PARAMETERS = "approve";

    private static final View REDIRECT_TO_ADMIN_APPROVE_STATUS_COMMAND = new View(ActionName.REDIRECT, PathConstant.COMMAND_ADMIN_APPROVE);

    @SimpleAutowire
    private UserService userService;

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.info("[POST] Update users approve status by admin.");

        try {
            String[] parameterValues = request.getParameterValues(APPROVE_PARAMETERS);
            String[] userIdStringArray = WebUtils.requiredNumberParameter(parameterValues, APPROVE_PARAMETERS);
            long[] userIdArray = Arrays.stream(userIdStringArray)
                    .mapToLong(Long::parseLong)
                    .toArray();
            userService.updateApproveStatus(userIdArray);
            return REDIRECT_TO_ADMIN_APPROVE_STATUS_COMMAND;
        } catch (ServiceException | WrongParameterException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

}
