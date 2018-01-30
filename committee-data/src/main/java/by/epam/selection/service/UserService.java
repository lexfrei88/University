package by.epam.selection.service;

import by.epam.selection.AuthenticatedUser;
import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Service layer interface for process {@link User} class objects
 *
 * @author Alex Aksionchik 12/13/2017
 * @version 1.0
 */
public interface UserService {

    /**
     * Save user to database
     *
     * @param user User object that contains data to save to database
     * @return User object with ID that has been auto generated in database
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    User save(User user) throws ServiceException;

    /**
     * Retrieve User data for the user with ID = {@code id}
     *
     * @param id ID of the user that should be get from database
     * @return User with data from database
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    User get(long id) throws ServiceException;

    /**
     * Update ID of the faculty that has been selected by user
     *
     * @param facultyId ID of the faculty that has been chosen
     * @param userId ID of the user that choose faculty
     * @return true if update complete correct, else - false
     * @throws ServiceException in case of any DaoException on DAO layer occur
     */
    boolean updateFaculty(long facultyId, long userId) throws ServiceException;

    /**
     * Get all user which has unapproved status
     *
     * @return List of User objects
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    List<User> getAllUnapproved() throws ServiceException;

    /**
     * Select all users that are suitable for criteria for specified faculty
     *
     * @param faculty faculty for what users should be selected
     * @return List of user that are suitable for criteria
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    List<User> getAllSelected(Faculty faculty) throws ServiceException;

    /**
     * Get authenticate user if it's credentials is valid
     * @param email user's email
     * @param password user's password
     * @return authenticated user object that contains credentials
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    AuthenticatedUser loadUserByUsername(String email, String password) throws ServiceException;

    /**
     * Update amount of users status to 'approved'
     *
     * @param userIdArray ID's of the users which status should be updated
     * @return true if all user were updated successfully
     * @throws ServiceException in case of any DaoException on DAO layer
     */
    boolean updateApproveStatus(long... userIdArray) throws ServiceException;
}
