package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.Faculty;

import java.util.List;

/**
 * @author Alex Aksionchik 12/9/2017
 * @version 0.1
 */
public interface FacultyDao {
    List<Faculty> getAll() throws DaoException;

    Faculty get(long facultyId) throws DaoException;
}
