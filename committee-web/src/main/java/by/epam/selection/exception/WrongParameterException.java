package by.epam.selection.exception;

/**
 * Exception that thrown when illegal parameter passed from client to a server
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public class WrongParameterException extends SelectionCommitteeException {

    public WrongParameterException() {
    }

    public WrongParameterException(String message) {
        super(message);
    }

    public WrongParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongParameterException(Throwable cause) {
        super(cause);
    }

}
