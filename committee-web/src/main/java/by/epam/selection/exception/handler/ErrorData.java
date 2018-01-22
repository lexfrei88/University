package by.epam.selection.exception.handler;

/**
 * Object that contains data about handled exception and some addition data.
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public class ErrorData {

    private Integer httpStatusCode;
    private String exceptionType;
    private String message;

    public ErrorData(Integer httpStatusCode, String exceptionType, String message) {
        this.httpStatusCode = httpStatusCode;
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }

}
