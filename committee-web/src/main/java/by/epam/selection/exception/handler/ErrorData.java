package by.epam.selection.exception.handler;

/**
 * Created by lex on 1/7/2018.
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
