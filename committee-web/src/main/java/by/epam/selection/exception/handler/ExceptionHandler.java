package by.epam.selection.exception.handler;

/**
 * Interface that handle exceptions on server by converting them into special object {@link ErrorData}
 * that contains addition data about occurred exception which will be passed to the client
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public interface ExceptionHandler {

    /**
     * Handle specified exception
     *
     * @param e - exception that was thrown
     * @return ErrorData object that contains addition data depends of exception cause
     */
    ErrorData handle(Exception e);
}
