package by.epam.selection.service;

import by.epam.selection.dao.FacultyDao;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Faculty;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Created by lex on 12/17/2017.
 */
public class FacultyServiceImpl implements FacultyService {

    private FacultyDao facultyDao;

    public FacultyServiceImpl(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public List<Faculty> getAll() throws ServiceException {
        try {
            return facultyDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Faculty get(long facultyId) throws ServiceException {
        try {
            return facultyDao.get(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
