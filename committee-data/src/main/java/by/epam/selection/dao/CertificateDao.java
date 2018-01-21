package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Certificate;

import java.util.List;

/**
 * Data access interface that work with {@link Certificate} entity
 *
 * @author Alex Aksionchik 12/5/2017
 * @version 1.0
 */
public interface CertificateDao {

    /**
     * Retrieve all certificates that belong to specified User
     *
     * @param userId - ID of the User which certificates
     * @return List of Certificates or empty List if user have no certificates
     * @throws DaoException if any exception occur while updating
     */
    List<Certificate> getAllForUser(long userId) throws DaoException;

    /**
     * Save Certificate in DB for specified User
     *
     * @param certificate - Certificate that should be saved in DB
     * @param userId - ID of the User which certificate belong
     * @return Certificate object with ID generated in database
     * @throws DaoException if any exception occur while updating
     */
    Certificate save(Certificate certificate, long userId) throws DaoException;

    /**
     * Update Certificate that exist in DB
     *
     * @param certificate - Certificate that contains data for update
     * @param userId - ID of the User that Certificate belong
     * @return updated Certificate or null if no rows affected
     * @throws DaoException if any exception occur while updating
     */
    Certificate update(Certificate certificate, long userId) throws DaoException;

}
