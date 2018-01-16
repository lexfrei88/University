package by.epam.selection.service;

import by.epam.selection.entity.Certificate;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Created by lex on 12/17/2017.
 */
public interface CertificateService {
    List<Certificate> getAllForUser(long userId) throws ServiceException;

    Certificate saveOrUpdate(Certificate certificate, long userId) throws ServiceException;
}
