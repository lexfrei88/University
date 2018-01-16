package by.epam.selection.exception.handler;

/**
 * Created by lex on 1/7/2018.
 */
public class ExceptionHandlerFactory {

    private static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandlerImpl();

    public ExceptionHandler create() {
        return EXCEPTION_HANDLER;
    }

}
