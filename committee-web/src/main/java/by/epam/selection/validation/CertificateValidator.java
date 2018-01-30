package by.epam.selection.validation;

import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Implementation of {@link Validator} interface for validating {@link Certificate} entity
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
 */
public class CertificateValidator implements Validator<Certificate> {

    private static final String MESSAGES_ERRORS_BUNDLE_PATH = "messages/errors";

    private static final String ID_ERROR_BUNDLE_KEY = "id.error";
    private static final String SCORE_ERROR_BUNDLE_KEY = "score.error";
    private static final String CERTIFICATE_ID_ERROR_KEY = "certificateIdError";
    private static final String SUBJECT_ID_ERROR_KEY = "subjectIdError";
    private static final String SCORE_ERROR_KEY = "scoreError";

    @Override
    public Map<String, String> validate(Locale locale, Certificate certificate) {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_ERRORS_BUNDLE_PATH, locale);
        Map<String, String> errorMessage = new HashMap<>();

        if (certificate.getId() != null && certificate.getId() < 0) {
            String message = bundle.getString(ID_ERROR_BUNDLE_KEY);
            errorMessage.put(CERTIFICATE_ID_ERROR_KEY, message);
        }

        Subject subject = certificate.getSubject();
        if (subject == null || subject.getId() == null || subject.getId() <= 0) {
            String message = bundle.getString(ID_ERROR_BUNDLE_KEY);
            errorMessage.put(SUBJECT_ID_ERROR_KEY, message);
        }
        int score = certificate.getScore();
        if (score < 0 || score > 10) {
            String message = bundle.getString(SCORE_ERROR_BUNDLE_KEY);
            errorMessage.put(SCORE_ERROR_KEY, message);
        }

        return errorMessage;
    }

}
