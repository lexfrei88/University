package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.User;

import java.util.List;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public interface UserDao {

    User save(User user) throws DaoException;

    User get(long id) throws DaoException;

    User getByEmail(String email) throws DaoException;

    boolean updateFaculty(long facultyId, long userId) throws DaoException;

    List<User> getAllUnapproved() throws DaoException;

    boolean updateApproveStatus(long userId) throws DaoException;

    List<User> getAllSelected(long facultyId, int studentLimit) throws DaoException;
}
