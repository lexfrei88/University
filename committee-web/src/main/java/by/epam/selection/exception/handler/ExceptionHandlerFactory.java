package by.epam.selection.exception.handler;

/**
 * Factory that create {@link ExceptionHandler} implementation object
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public class ExceptionHandlerFactory {

    private static final ExceptionHandler EXCEPTION_HANDLER = new GlobalExceptionHandler();

    public ExceptionHandler create() {
        return EXCEPTION_HANDLER;
    }

}
