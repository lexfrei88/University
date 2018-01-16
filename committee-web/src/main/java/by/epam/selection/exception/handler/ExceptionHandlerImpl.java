package by.epam.selection.exception.handler;

import by.epam.selection.exception.WrongParameterException;
import by.epam.selection.service.exception.NotFoundException;
import by.epam.selection.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by lex on 1/7/2018.
 */
public class ExceptionHandlerImpl implements ExceptionHandler {

    private static final Integer SC_UNPROCESSABLE_ENTITY = 422;

    private static final String EMAIL_DUPLICATION_MSG = "Email already exist in DB.";
    private static final String EMAIL_UNIQUE_INDEX_NAME = "idx_unique_user_email";

    private static final String NOT_FOUND_EXCEPTION = "NotFoundException";
    private static final String WRONG_PARAMETER_EXCEPTION = "WrongParameterException";
    private static final String DATA_INTEGRITY_VIOLATION_EXCEPTION = "DataIntegrityViolationException";

    @Override
    public ErrorData handle(Exception e) {
        Throwable root = WebUtils.getExceptionRootCause(e);

        if (root instanceof NotFoundException) {
            return new ErrorData(HttpServletResponse.SC_NOT_FOUND, NOT_FOUND_EXCEPTION, e.getMessage());
        }

        if (root instanceof WrongParameterException) {
            return new ErrorData(SC_UNPROCESSABLE_ENTITY, WRONG_PARAMETER_EXCEPTION, e.getMessage());
        }

        if (root instanceof SQLIntegrityConstraintViolationException) {
            String msg = root.getMessage();
            if (msg.contains(EMAIL_UNIQUE_INDEX_NAME)) {
                return new ErrorData(HttpServletResponse.SC_CONFLICT, DATA_INTEGRITY_VIOLATION_EXCEPTION, EMAIL_DUPLICATION_MSG);
            }
        }

        return new ErrorData(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getClass().getName(), e.getMessage());
    }

}
