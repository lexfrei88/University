package by.epam.selection.service.exception;

import by.epam.selection.exception.SelectionCommitteeException;

/**
 * Exception that could be thrown on a Service Layer.
 *
 * @author Alex Aksionchik 12/17/2017
 * @version 1.0
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
