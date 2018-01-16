package by.epam.selection.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public final class DaoUtils {

    private DaoUtils() {
    }

    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given parameter values.
     *
     * @param connection          The Connection to create the PreparedStatement from.
     * @param sql                 The SQL query to construct the PreparedStatement with.
     * @param values              The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during creating the PreparedStatement.
     */
    public static PreparedStatement prepareStatement(Connection connection, String sql, Object... values) throws SQLException {
        return getPreparedStatement(connection, sql, false, values);
    }

    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given parameter values. This PreparedStatement after executeQuery method invoke will return ResultSet with
     * generated keys.
     *
     * @param connection          The Connection to create the PreparedStatement from.
     * @param sql                 The SQL query to construct the PreparedStatement with.
     * @param values              The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during creating the PreparedStatement.
     */
    public static PreparedStatement prepareStatementWithId(Connection connection, String sql, Object... values) throws SQLException {
        return getPreparedStatement(connection, sql, true, values);
    }

    private static PreparedStatement getPreparedStatement(Connection connection, String sql, boolean isGeneratedKeys, Object[] values) throws SQLException {
        int returnGeneratedKeys = isGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
        PreparedStatement statement = connection.prepareStatement(sql, returnGeneratedKeys);
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
        return statement;
    }

}
