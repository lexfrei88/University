package by.epam.selection.dao.connection.pool;

import by.epam.selection.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link ConnectionPool} interface.
 *
 * @author Alex Aksionchik 12/19/2017
 * @version 1.0
 */
public class ConnectionPoolImpl implements ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPoolImpl.class);

    private static final String DEFAULT_DB_PROPERTIES_FILE_PATH = "/db.xml";

    private static final String DRIVER_NAME_PROPERTY = "driverName";
    private static final String URL_PROPERTY = "url";
    private static final String MAX_TOTAL_PROPERTY = "maxTotal";
    private static final String MAX_WAIT_MILLIS_PROPERTY = "maxWaitMillis";
    private static final String USER_NAME_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";

    private BlockingQueue<Connection> connectionPool;
    private Properties properties;

    private ConnectionPoolImpl(BlockingQueue<Connection> connectionPool, Properties properties) {
        this.connectionPool = connectionPool;
        this.properties = properties;
    }

    /**
     * Factory method that create ConnectionPool instance from properties file from classpath that have
     * name {@code db.xml}
     *
     * @return ConnectionPool instance
     */
    public static ConnectionPoolImpl create() {
        return create(DEFAULT_DB_PROPERTIES_FILE_PATH);
    }

    /**
     * Factory method that create ConnectionPool instance from specified properties file from classpath
     *
     * @param dbPropertiesFilePath - path to properties file from classpath
     * @return ConnectionPool instance
     */
    public static ConnectionPoolImpl create(String dbPropertiesFilePath) {
        BlockingQueue<Connection> connectionPool;
        Properties properties;
        try (InputStream is = ConnectionPoolImpl.class.getResourceAsStream(dbPropertiesFilePath)) {
            properties = new Properties();
            properties.loadFromXML(is);

            String driverName = properties.getProperty(DRIVER_NAME_PROPERTY);
            Class.forName(driverName);

            int maxTotal = Integer.parseInt(properties.getProperty(MAX_TOTAL_PROPERTY));
            connectionPool = new ArrayBlockingQueue<>(maxTotal, true);
            String url = properties.getProperty(URL_PROPERTY);
            for (int i = 0; i < maxTotal; i++) {
                connectionPool.offer(DriverManager.getConnection(url, properties));
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return new ConnectionPoolImpl(connectionPool, properties);
    }

    /**
     * Factory method that create ConnectionPool instance with parameters passed by method signature.
     *
     * @param driverName    - SQL driver name
     * @param maxTotal      - max amount of connection in the pool
     * @param maxWaitMillis - max time to wait for connection while connecting
     * @param url           - path to the database
     * @param userName      - login to connect to the database
     * @param password      - password to the database
     * @return ConnectionPool instance
     */
    public static ConnectionPoolImpl create(String driverName, String maxTotal, String maxWaitMillis, String url, String userName, String password) {
        BlockingQueue<Connection> connectionPool;
        Properties properties = buildProperties(driverName, maxTotal, maxWaitMillis, url, userName, password);
        try {
            Class.forName(driverName);

            int maxTotalInt = Integer.parseInt(maxTotal);
            connectionPool = new ArrayBlockingQueue<>(maxTotalInt, true);
            for (int i = 0; i < maxTotalInt; i++) {
                connectionPool.offer(DriverManager.getConnection(url, properties));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return new ConnectionPoolImpl(connectionPool, properties);
    }

    @Override
    public Connection getConnection() {
        Connection connection;
        try {
            int maxWaitMillis = Integer.parseInt(properties.getProperty(MAX_WAIT_MILLIS_PROPERTY));
            connection = connectionPool.poll(maxWaitMillis, TimeUnit.MILLISECONDS);
            if (connection == null) {
                throw new SQLException("Could not obtain connection from pool.");
            }
            if (!connection.isValid(0)) {
                String url = properties.getProperty(URL_PROPERTY);
                connection = DriverManager.getConnection(url, properties);
            }
        } catch (InterruptedException | SQLException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return connection;
    }

    @Override
    public void close(Connection connection) {
        connectionPool.offer(connection);
    }

    @Override
    public void destroy() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            }
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }

    private static Properties buildProperties(String driverName, String maxTotal, String maxWaitMillis, String url, String userName, String password) {
        Properties properties = new Properties();
        properties.setProperty(DRIVER_NAME_PROPERTY, driverName);
        properties.setProperty(MAX_TOTAL_PROPERTY, maxTotal);
        properties.setProperty(MAX_WAIT_MILLIS_PROPERTY, maxWaitMillis);
        properties.setProperty(URL_PROPERTY, url);
        properties.setProperty(USER_NAME_PROPERTY, userName);
        properties.setProperty(PASSWORD_PROPERTY, password);

        return properties;
    }

}
