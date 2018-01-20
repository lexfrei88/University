package by.epam.selection.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection holder that should serve connection life cycle during the request.
 *
 * @author Alex Aksionchik 12/3/2017
 * @version 1.0
 */
public interface ConnectionHolder {

    /**
     * Receive connection with database for request.
     *
     * @return Connection object that represent connection with the database
     */
    Connection getConnection();

    /**
     * Release connection after all operations during the request are complete.
     */
    void close() throws SQLException;

}
