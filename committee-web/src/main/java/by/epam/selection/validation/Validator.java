package by.epam.selection.validation;

import by.epam.selection.entity.Identifiable;

import java.util.Set;

/**
 * Interface for validating Entity
 *
 * {@see ConstraintViolation}
 *
 * @author Alex Aksionchik 12/21/2017
 * @version 1.0
 */
public interface Validator<T extends Identifiable> {

    /**
     * Validate either specified entity has valid data or not
     *
     * @param obj - entity should be validated
     * @return Set of {@link ConstraintViolation} object each of that contains data about invalid data in entity
     * or empty Set if entity is valid
     */
    Set<ConstraintViolation> validate(T obj);
}
