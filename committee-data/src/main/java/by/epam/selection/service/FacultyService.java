package by.epam.selection.service;

import by.epam.selection.entity.Faculty;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Service layer interface for process {@link Faculty} class objects
 *
 * @author Alex Aksionchik 12/17/2017
 * @version 1.0
 */
public interface FacultyService {

    /**
     * Retrieve all faculties from database
     *
     * @return List of Faculties or empty List
     * @throws ServiceException in case any DaoException on DAO layer
     */
    List<Faculty> getAll() throws ServiceException;

    /**
     * Get faculty with ID = {@code facultyId}
     * @param facultyId - ID of the Faculty that should be get from database
     * @return Faculty object
     * @throws ServiceException in case any DaoException on DAO layer
     */
    Faculty get(long facultyId) throws ServiceException;
}
