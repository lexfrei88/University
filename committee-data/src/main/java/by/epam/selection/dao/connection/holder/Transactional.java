package by.epam.selection.dao.connection.holder;

import by.epam.selection.dao.exception.DaoException;

import java.sql.SQLException;

/**
 * Serve the Transaction support.
 *
 * @author Alex Aksionchik 12/3/2017
 * @version 1.0
 */
public interface Transactional {

    /**
     * Receive Connection with auto commit properties set to false.
     * @throws DaoException in case of any SQL exception while getting connection
     */
    void getTxConnection() throws DaoException;

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
