package by.epam.selection.dao.template.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lex on 12/13/2017.
 */
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
