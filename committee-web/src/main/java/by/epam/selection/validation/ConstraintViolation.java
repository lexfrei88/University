package by.epam.selection.validation;

/**
 * Object that contain data about violations in entity during validating it with {@link Validator}
 *
 * {@see Validator}
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
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
