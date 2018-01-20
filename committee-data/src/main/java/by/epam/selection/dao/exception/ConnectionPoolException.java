package by.epam.selection.dao.exception;

/**
 * Exception that could be occur during initialization of the connection poll.
 * <p>
 * <b>WARNING!</b> There's no reason to execute application if that exception occur, because that mean
 * you don't have normal Connection Pool or Connection to database.
 * That's why it's inherited not from {@link Exception}, but from {@link RuntimeException}.
 *
 * @author Alex Aksionchik 12/20/2017
 * @version 1.0
 */
public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

}
