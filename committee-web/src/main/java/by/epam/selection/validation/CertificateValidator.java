package by.epam.selection.validation;

import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link Validator} interface for validating {@link Certificate} entity
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
 */
public class CertificateValidator implements Validator<Certificate> {

    private static final String ID_ERROR_MSG = "ID must be greater than 0";
    private static final String SCORE_ERROR_MSG = "Score must be from 0 to 10 inclusive";

    @Override
    public Set<ConstraintViolation> validate(Certificate certificate) {
        Set<ConstraintViolation> violations = new HashSet<>();

        if (certificate.getId() != null && certificate.getId() < 0) {
            violations.add(new ConstraintViolation("certificateIdError", ID_ERROR_MSG));
        }

        Subject subject = certificate.getSubject();
        if (subject == null || subject.getId() == null || subject.getId() <= 0) {
            violations.add(new ConstraintViolation("subjectIdError", ID_ERROR_MSG));
        }
        int score = certificate.getScore();
        if (score < 0 || score > 10) {
            violations.add(new ConstraintViolation("scoreError", SCORE_ERROR_MSG));
        }

        return violations;
    }

}
