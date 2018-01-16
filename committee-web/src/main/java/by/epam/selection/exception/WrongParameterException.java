package by.epam.selection.exception;

/**
 * Created by lex on 1/7/2018.
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
