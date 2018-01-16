package by.epam.selection.validation;

import by.epam.selection.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lex on 12/21/2017.
 */
public class UserValidator implements Validator<User> {

    private static final String EMAIL_REGEX = "[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-z]{2,5}";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
    private static final String NAME_REGEX = "[a-zA-Z]+";

    @Override
    public Set<ConstraintViolation> validate(User user) {
        Set<ConstraintViolation> violations = new HashSet<>();

        if (user == null) {
            ConstraintViolation violation = new ConstraintViolation("userError", "User can't be null.");
            violations.add(violation);
        } else {
            String email = user.getEmail();
            if (email == null || !email.matches(EMAIL_REGEX)) {
                violations.add(new ConstraintViolation("emailError", "Email is not valid. Must be in pattern 'example@mail.com'"));
            }

            String password = user.getPassword();
            if (password == null || !password.matches(PASSWORD_REGEX)) {
                violations.add(new ConstraintViolation("passwordError", "Password is not valid. Must have one capital, one small, one " +
                        "digit and length at least 6 symbols"));
            }

            String firstName = user.getFirstName();
            if (firstName == null || !firstName.matches(NAME_REGEX)) {
                violations.add(new ConstraintViolation("firstNameError", "Must have at least one letter."));
            }

            String lastName = user.getLastName();
            if (lastName == null || !lastName.matches(NAME_REGEX)) {
                violations.add(new ConstraintViolation("lastNameError", "Must have at least one letter."));
            }
        }
        return violations;
    }

}
