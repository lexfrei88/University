package by.epam.selection.validation;

import by.epam.selection.entity.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Implementation of {@link Validator} interface for validating {@link User} entity
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
 */
public class UserValidator implements Validator<User> {

    private static final String MESSAGES_ERRORS_BUNDLE_PATH = "messages/errors";

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]+");

    private static final String EMAIL_ERROR_BUNDLE_KEY = "email.error";
    private static final String EMAIL_ERROR_KEY = "email";
    private static final String PASSWORD_ERROR_BUNDLE_KEY = "password.error";
    private static final String PASSWORD_ERROR_KEY = "password";
    private static final String FIRST_NAME_ERROR_BUNDLE_KEY = "first.name.error";
    private static final String FIRST_NAME_ERROR_KEY = "firstName";
    private static final String LAST_NAME_ERROR_BUNDLE_KEY = "last.name.error";
    private static final String LAST_NAME_ERROR_KEY = "lastName";

    @Override
    public Map<String, String> validate(Locale locale, User user) {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_ERRORS_BUNDLE_PATH, locale);
        Map<String, String> errorMessage = new HashMap<>();

        String email = user.getEmail();
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            String message = bundle.getString(EMAIL_ERROR_BUNDLE_KEY);
            errorMessage.put(EMAIL_ERROR_KEY, message);
        }

        String password = user.getPassword();
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            String message = bundle.getString(PASSWORD_ERROR_BUNDLE_KEY);
            errorMessage.put(PASSWORD_ERROR_KEY, message);
        }

        String firstName = user.getFirstName();
        if (firstName == null || !NAME_PATTERN.matcher(firstName).matches()) {
            String message = bundle.getString(FIRST_NAME_ERROR_BUNDLE_KEY);
            errorMessage.put(FIRST_NAME_ERROR_KEY, message);
        }

        String lastName = user.getLastName();
        if (lastName == null || !NAME_PATTERN.matcher(lastName).matches()) {
            String message = bundle.getString(LAST_NAME_ERROR_BUNDLE_KEY);
            errorMessage.put(LAST_NAME_ERROR_KEY, message);
        }

        return errorMessage;
    }

}
