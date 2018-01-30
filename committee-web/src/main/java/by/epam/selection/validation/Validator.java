package by.epam.selection.validation;

import by.epam.selection.entity.Identifiable;

import java.util.Locale;
import java.util.Map;

/**
 * Interface for validating Entity
 * <p>
 * {@see ConstraintViolation}
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
 */
public interface Validator<T extends Identifiable> {

    /**
     * Validate either specified entity has valid data or not
     *
     * @param locale - current request locale
     * @param obj    - entity should be validated
     * @return Map that contains error message for each violation
     */
    Map<String, String> validate(Locale locale, T obj);
}
