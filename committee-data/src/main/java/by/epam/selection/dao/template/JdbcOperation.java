package by.epam.selection.dao.template;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.template.extractor.ResultSetExtractor;
import by.epam.selection.dao.template.mapper.RowMapper;

import java.util.List;

/**
 * Created by lex on 12/15/2017.
 */
public interface JdbcOperation {
    Long save(String sql, Object... values) throws DaoException;

    int update(String sql, Object... values) throws DaoException;

    <T> T query(String sql, ResultSetExtractor<T> resultSetExtractor, Object... values) throws DaoException;

    <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... values) throws DaoException;

    <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) throws DaoException;
}
