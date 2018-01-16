package by.epam.selection.service.exception;

import by.epam.selection.exception.SelectionCommitteeException;

/**
 * Created by lex on 12/17/2017.
 */
public class ServiceException extends SelectionCommitteeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
