package by.epam.selection.dao.connection.holder;

import by.epam.selection.dao.connection.pool.ConnectionPool;
import by.epam.selection.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of {@link ConnectionHolder} and {@link Transactional} interfaces.
 *
 * Serve the connection lifecycle during the request and support operations in transaction mode.
 *
 * @author Alex Aksionchik 12/3/2017
 * @version 1.0
 */
public class ConnectionHolderImpl implements ConnectionHolder, Transactional {

    private ConnectionPool connectionPool;

    private ThreadLocal<Connection> connection = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            return connectionPool.getConnection();
        }
    };

    public ConnectionHolderImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        return connection.get();
    }

    @Override
    public void close() throws SQLException {
        Connection conn = connection.get();
        conn.setAutoCommit(true);
        connectionPool.close(conn);
        connection.remove();
    }

    @Override
    public void getTxConnection() throws DaoException {
        Connection conn;
        try {
            conn = connection.get();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void commitTx() throws DaoException {
        try {
            Connection conn = connection.get();
            conn.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void rollbackTx() throws DaoException {
        try {
            Connection conn = connection.get();
            conn.rollback();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
