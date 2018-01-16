package by.epam.selection.validation;

import by.epam.selection.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by lex on 1/5/2018.
 */
public class UserValidatorTest {

    private static final long USER_ID = 1L;
    private static final String INVALID_NAME = "invalid_name";
    private static final String INVALID_SURNAME = "invalid_surname";
    private static final String INVALID_MAIL = "invalid_mail";

    @Test
    public void shouldFourConstraintsWhenValidateUser() throws Exception {
        UserValidator userValidator = new UserValidator();
        User tested = new User(USER_ID, INVALID_NAME, INVALID_SURNAME, INVALID_MAIL, false, null);
        Set<ConstraintViolation> violations = userValidator.validate(tested);
        Assert.assertEquals(4, violations.size());
    }

}
