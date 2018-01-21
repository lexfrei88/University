package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.entity.User;

import java.util.List;

/**
 * Data access interface that work with {@link User} entity
 *
 * @author Alex Aksionchik 12/3/2017
 * @version 1.0
 */
public interface UserDao {

    /**
     * Save User in DB
     *
     * @param user - User object that should be saved in database
     * @return User object with auto generated ID
     * @throws DaoException if any SQL exception occur
     */
    User save(User user) throws DaoException;

    /**
     * Get User by ID
     *
     * @param id - ID of the User that should be retrieved
     * @return User object or {@code null} if no User with specified {@code ID} was found
     * @throws DaoException if any SQL exception occur
     */
    User get(long id) throws DaoException;

    /**
     * Get User by email
     *
     * @param email - E-mail of the User that should be retrieved
     * @return User object or {@code null} if no User with specified {@code E-mail} was found
     * @throws DaoException if any SQL exception occur
     */
    User getByEmail(String email) throws DaoException;

    /**
     * Update data about Faculty that was chosen by User
     *
     * @param facultyId - new Faculty ID
     * @param userId - User ID that faculty should be updated
     * @return true if update affected at least one row, else - false
     * @throws DaoException if any SQL exception occur
     */
    boolean updateFaculty(long facultyId, long userId) throws DaoException;

    /**
     * Get List of Users that have "unapproved" status
     *
     * @return List of Users that have "unapproved" status or empty List
     * @throws DaoException if any SQL exception occur
     */
    List<User> getAllUnapproved() throws DaoException;

    /**
     * Update "approve" status of specified User
     *
     * @param userId - ID of the User which status should be updated
     * @return true if update affected at least one row, else - false
     * @throws DaoException if any SQL exception occur
     */
    boolean updateApproveStatus(long userId) throws DaoException;

    /**
     * Get List of Users with best sum of scores for subjects for specified Faculty
     * and List size is equal to {@code studentLimit}
     *
     * @param facultyId - ID of Faculty for what Users should be selected
     * @param studentLimit - Limit of Users that should be selected
     * @return List of Users or empty List
     * @throws DaoException if any SQL exception occur
     */
    List<User> getAllSelected(long facultyId, int studentLimit) throws DaoException;

}
