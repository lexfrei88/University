package by.epam.selection.exception;

/**
 * Created by lex on 1/7/2018.
 */
public class WebException extends SelectionCommitteeException {

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

}
