package by.epam.selection.dao.connection.pool;

import java.sql.Connection;

/**
 * Connection pool that keep the number of connections with database.
 *
 * @author Alex Aksionchik 12/5/2017
 * @version 1.0
 */
public interface ConnectionPool {

    /**
     * Receive alive connection from the pool.
     *
     * @return Connection object with database
     */
    Connection getConnection();

    /**
     * Return connection into the pool.
     *
     * @param connection that should be returned into the pool
     */
    void close(Connection connection);

    /**
     * Closing all connections in pool. Should be invoked during the application stop.
     */
    void destroy();

}
