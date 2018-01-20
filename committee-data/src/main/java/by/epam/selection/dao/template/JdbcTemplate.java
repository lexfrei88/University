package by.epam.selection.dao.template;

import by.epam.selection.dao.connection.ConnectionHolder;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.template.extractor.ListResultSetExtractor;
import by.epam.selection.dao.template.extractor.ResultSetExtractor;
import by.epam.selection.dao.template.extractor.SingleRowResultSetExtractor;
import by.epam.selection.dao.template.mapper.RowMapper;
import by.epam.selection.dao.util.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link JdbcOperation} interface. Base class for DAO implementation.
 *
 * @author Alex Aksionchik 12/13/2017
 * @version 1.0
 */
public class JdbcTemplate implements JdbcOperation {

    private static final Logger logger = LogManager.getLogger(JdbcTemplate.class);

    private ConnectionHolder connectionHolder;

    public JdbcTemplate(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Override
    public Long save(String sql, Object... values) throws DaoException {
        Long id = null;

        Connection conn = connectionHolder.getConnection();
        try (PreparedStatement statement = DaoUtils.prepareStatementWithId(conn, sql, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Operation failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            release(conn);
        }
        return id;
    }

    @Override
    public int update(String sql, Object... values) throws DaoException {
        int affectedRows;

        Connection conn = connectionHolder.getConnection();
        try (PreparedStatement statement = DaoUtils.prepareStatement(conn, sql, values)) {
            affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Operation failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            release(conn);
        }
        return affectedRows;
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> resultSetExtractor, Object... values) throws DaoException {
        T obj;

        Connection conn = connectionHolder.getConnection();
        try (PreparedStatement statement = DaoUtils.prepareStatement(conn, sql, values);
             ResultSet rs = statement.executeQuery()) {
            obj = resultSetExtractor.extractData(rs);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            release(conn);
        }
        return obj;
    }

    @Override
    public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... values) throws DaoException {
        return query(sql, new ListResultSetExtractor<>(rowMapper), values);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) throws DaoException {
        return query(sql, new SingleRowResultSetExtractor<>(rowMapper), values);
    }

    private void release(Connection connection) throws DaoException {
        try {
            if (connection != null && connection.getAutoCommit()) {
                connectionHolder.close();
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

}
