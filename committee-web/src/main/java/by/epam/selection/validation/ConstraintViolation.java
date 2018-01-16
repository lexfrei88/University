package by.epam.selection.validation;

/**
 * Created by lex on 12/21/2017.
 */
public class ConstraintViolation {

    private String key;
    private String message;

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public ConstraintViolation(String key, String message) {
        this.key = key;
        this.message = message;
    }

}
