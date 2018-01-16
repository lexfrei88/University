package by.epam.selection.dao.template.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transform the {@link ResultSet} into and object.
 *
 * @param <T> type of the object to compose from ResultSet data.
 */
public interface ResultSetExtractor<T> {

    /**
     * Extract data from {@link ResultSet} and transform them in into an object.
     *
     * @param resultSet from what should be retrieved data.
     * @return object full with data from resultSet
     * @throws SQLException could be occur during read from result set.
     */
    T extractData(ResultSet resultSet) throws SQLException;
}
