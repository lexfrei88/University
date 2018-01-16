package by.epam.selection.validation;

import java.util.Set;

/**
 * Created by lex on 12/21/2017.
 */
public interface Validator<T> {
    Set<ConstraintViolation> validate(T obj);
}
