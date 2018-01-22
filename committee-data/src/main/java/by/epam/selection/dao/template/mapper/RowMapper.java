package by.epam.selection.dao.template.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transform a single row of the {@link ResultSet} to an entity
 *
 * @author Alex Aksionchik 12/13/2017
 * @version 1.0
 */
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
