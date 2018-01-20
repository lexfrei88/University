package by.epam.selection.dao.connection;

import by.epam.selection.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Serve the Transaction support.
 */
public interface Transactional {

    /**
     * Receive Connection with auto commit properties set to false.
     * @return a Connection to the database
     * @throws DaoException in case of any SQL exception while getting connection
     */
    Connection getTxConnection() throws DaoException;

    /**
     * Makes all changes made since the previous commit/rollback permanent.
     * @throws DaoException in case of any SQL exception while commit
     */
    void commitTx() throws DaoException;

    /**
     * Undoes all changes made in the current transaction and releases.
     * @throws DaoException in case of any SQL exception while commit
     */
    void rollbackTx() throws DaoException;

    /**
     * Release connection after all operations during the transaction are complete.
     */
    void close() throws SQLException;

}
