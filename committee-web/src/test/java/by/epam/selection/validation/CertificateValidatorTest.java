package by.epam.selection.validation;

import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.Map;

/**
 * Created by lex on 1/5/2018.
 */
public class CertificateValidatorTest {

    private static final long INVALID_SUBJECT_ID = -1L;
    private static final int INVALID_SCORE = 11;
    private static final String SUBJECT_NAME = "name";
    private static final long VALID_SUBJECT_ID = 1L;

    @Test
    public void shouldTwoConstraintWhenValidate() throws Exception {
        Subject subject = new Subject(INVALID_SUBJECT_ID, SUBJECT_NAME);
        Certificate certificate = new Certificate(subject, INVALID_SCORE);
        CertificateValidator validator = new CertificateValidator();
        Map<String, String> violations = validator.validate(Locale.ENGLISH, certificate);
        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void shouldOneConstraintWhenValidate() throws Exception {
        Subject subject = new Subject(VALID_SUBJECT_ID, SUBJECT_NAME);
        Certificate certificate = new Certificate(subject, INVALID_SCORE);
        CertificateValidator validator = new CertificateValidator();
        Map<String, String> violations = validator.validate(Locale.ENGLISH, certificate);
        Assert.assertEquals(1, violations.size());
    }

}