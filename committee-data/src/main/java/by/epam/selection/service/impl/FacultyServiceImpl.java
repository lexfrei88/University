package by.epam.selection.service.impl;

import by.epam.selection.dao.FacultyDao;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Faculty;
import by.epam.selection.service.FacultyService;
import by.epam.selection.service.exception.NotFoundException;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Implementation of {@link FacultyService} interface
 *
 * @author Alex Aksionchik 12/17/2017
 * @version 1.0
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
        Faculty faculty;
        try {
            faculty = facultyDao.get(facultyId);
            if (faculty == null) {
                String msg = "Faculty not found with id = " + facultyId;
                throw new NotFoundException(msg);
            }
        } catch (DaoException | NotFoundException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return faculty;
    }

}
