package by.epam.selection.dao.template.extractor;

import by.epam.selection.dao.template.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lex on 12/16/2017.
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
