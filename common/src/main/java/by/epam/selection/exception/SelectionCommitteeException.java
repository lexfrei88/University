package by.epam.selection.exception;

/**
 * Created by lex on 12/21/2017.
 */
public class SelectionCommitteeException extends Exception {

    public SelectionCommitteeException() {
    }

    public SelectionCommitteeException(String message) {
        super(message);
    }

    public SelectionCommitteeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectionCommitteeException(Throwable cause) {
        super(cause);
    }

}
