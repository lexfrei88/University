package by.epam.selection.service.impl;

import by.epam.selection.dao.CertificateDao;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Certificate;
import by.epam.selection.service.CertificateService;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Implementation of {@link CertificateService} interface
 *
 * @author Alex Aksionchik 12/17/2017
 * @version 1.0
 */
public class CertificateServiceImpl implements CertificateService {

    private CertificateDao certificateDao;

    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public List<Certificate> getAllForUser(long userId) throws ServiceException {
        try {
            return certificateDao.getAllForUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Certificate saveOrUpdate(Certificate certificate, long userId) throws ServiceException {
        try {
            if (certificate.getId() == null) {
                return certificateDao.save(certificate, userId);
            } else {
                return certificateDao.update(certificate, userId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
