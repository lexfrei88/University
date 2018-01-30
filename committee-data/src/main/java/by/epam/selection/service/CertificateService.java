package by.epam.selection.service;

import by.epam.selection.entity.Certificate;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Service layer interface for process {@link Certificate} class objects
 * <p>
 * @author Alex Aksionchik 12/17/2017
 * @version 1.0
 */
public interface CertificateService {

    /**
     * Get all certificates for user with ID = {@code userId}
     *
     * @param userId - ID of the user which certificates should be retrieved from database
     * @return {@code List} of Certificate objects
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    List<Certificate> getAllForUser(long userId) throws ServiceException;

    /**
     * Save or update certificate for user with ID = {@code userId}
     *
     * @param certificate - certificate data that should be saved or updated
     * @param userId - ID of the user which certificates should be saved or updated
     * @return Certificate with auto generated ID from database
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    Certificate saveOrUpdate(Certificate certificate, long userId) throws ServiceException;
}
