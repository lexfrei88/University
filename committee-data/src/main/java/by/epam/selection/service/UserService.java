package by.epam.selection.service;

import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.AuthenticatedUser;

import java.util.List;

/**
 * Created by lex on 12/13/2017.
 */
public interface UserService {
    User save(User user) throws ServiceException;

    User get(long id) throws ServiceException;

    boolean updateFaculty(long facultyId, long userId) throws ServiceException;

    List<User> getAllUnapproved() throws ServiceException;

    List<User> getAllSelected(Faculty faculty) throws ServiceException;

    AuthenticatedUser loadUserByUsername(String email, String password) throws ServiceException;

    boolean updateApproveStatus(long... userIdArray) throws ServiceException;
}
