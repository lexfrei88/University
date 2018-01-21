package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Faculty;

import java.util.List;

/**
 * Data access interface that work with {@link Faculty} entity
 *
 * @author Alex Aksionchik 12/9/2017
 * @version 1.0
 */
public interface FacultyDao {

    /**
     * Get List of all Faculties
     *
     * @return List of all Faculties or empty List if no Faculties available
     * @throws DaoException if any SQL exception occur
     */
    List<Faculty> getAll() throws DaoException;

    /**
     * Get Faculty by ID
     *
     * @param facultyId - ID of the Faculty that should be retrieved
     * @return Faculty object
     * @throws DaoException if any SQL exception occur
     */
    Faculty get(long facultyId) throws DaoException;

}
