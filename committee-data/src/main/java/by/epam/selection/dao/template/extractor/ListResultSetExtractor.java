package by.epam.selection.dao.template.extractor;

import by.epam.selection.dao.template.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extract all rows from the {@link ResultSet} and transform them into List of entities
 *
 * @author Alex Aksionchik 12/14/2017
 * @version 1.0
 */
public class ListResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private RowMapper<T> rowMapper;

    public ListResultSetExtractor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public List<T> extractData(ResultSet resultSet) throws SQLException {
        List<T> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(rowMapper.mapRow(resultSet));
        }
        return results;
    }

}
