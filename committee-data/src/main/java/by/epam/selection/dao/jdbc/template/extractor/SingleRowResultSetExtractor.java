package by.epam.selection.dao.jdbc.template.extractor;

import by.epam.selection.dao.jdbc.template.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract the first row from {@link ResultSet} and transform it to the entity
 *
 * @author Alex Aksionchik 12/16/2017
 * @version 1.0
 */
public class SingleRowResultSetExtractor<T> implements ResultSetExtractor<T> {

    private RowMapper<T> rowMapper;

    public SingleRowResultSetExtractor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public T extractData(ResultSet resultSet) throws SQLException {
        T obj = null;
        if (resultSet.first()) {
            obj = rowMapper.mapRow(resultSet);
        }
        return obj;
    }

}
