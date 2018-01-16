package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Certificate;

import java.util.List;

/**
 * @author Alex Aksionchik 12/5/2017
 * @version 0.1
 */
public interface CertificateDao {
    List<Certificate> getAllForUser(long userId) throws DaoException;

    Certificate save(Certificate certificate, long userId) throws DaoException;

    Certificate update(Certificate certificate, long userId) throws DaoException;
}
