package by.epam.selection.service.exception;

import by.epam.selection.exception.SelectionCommitteeException;

/**
 * Created by lex on 12/13/2017.
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
