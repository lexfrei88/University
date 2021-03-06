package by.epam.selection.service.exception;

import by.epam.selection.exception.SelectionCommitteeException;

/**
 * Exception that could be thrown if entity not found
 *
 * @author Alex Aksionchik 12/13/2017
 * @version 1.0
 */
public class NotFoundException extends SelectionCommitteeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
