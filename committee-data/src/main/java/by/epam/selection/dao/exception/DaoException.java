package by.epam.selection.dao.exception;

import by.epam.selection.exception.SelectionCommitteeException;

/**
 * Exception that could occur on DAO layer.
 *
 * @author Alex Aksionchik 12/3/2017
 * @version 1.0
 */
public class DaoException extends SelectionCommitteeException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
