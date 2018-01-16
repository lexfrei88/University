package by.epam.selection.entity;

import java.io.Serializable;

/**
 * Created by lex on 12/19/2017.
 */
public interface Identifiable extends Serializable {
    Long getId();

    void setId(Long id);
}
