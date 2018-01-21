package by.epam.selection.service;

import by.epam.selection.dao.UserDao;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.tx.Transaction;
import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.Role;
import by.epam.selection.entity.User;
import by.epam.selection.service.exception.NotFoundException;
import by.epam.selection.service.exception.ServiceException;
import by.epam.selection.AuthenticatedUser;
import by.epam.selection.service.util.SecurityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lex on 12/13/2017.
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transaction
    public User save(User user) throws ServiceException {
        try {
            Set<Role> roles = EnumSet.of(Role.ROLE_USER);
            user.setRoles(roles);
            return userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User get(long id) throws ServiceException {
        User user;
        try {
            user = userDao.get(id);
            if (user == null) {
                String msg = "Don't have user with id: " + id;
                throw new NotFoundException(msg);
            }
        } catch (DaoException | NotFoundException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean updateFaculty(long facultyId, long userId) throws ServiceException {
        try {
            return userDao.updateFaculty(facultyId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getAllUnapproved() throws ServiceException {
        try {
            return userDao.getAllUnapproved();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getAllSelected(Faculty faculty) throws ServiceException {
        try {
            Long facultyId = faculty.getId();
            int studentLimit = faculty.getStudentLimit();
            return userDao.getAllSelected(facultyId, studentLimit);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transaction
    public boolean updateApproveStatus(long... userIdArray) throws ServiceException {
        logger.info("Update selected unapproved users.");
        for (long userId : userIdArray) {
            try {
                userDao.updateApproveStatus(userId);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        return true;
    }

    @Override
    public AuthenticatedUser loadUserByUsername(String email, String password) throws ServiceException {
        AuthenticatedUser authenticatedUser;
        try {
            User user = userDao.getByEmail(email);
            authenticatedUser = login(user, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return authenticatedUser;
    }

    private AuthenticatedUser login(User user, String password) {
        Long userId = user.getId();
        Set<Role> roles = user.getRoles();
        String userPassword = user.getPassword();
        String passwordHash = SecurityUtils.md5Hex(password);
        return passwordHash.equalsIgnoreCase(userPassword) ? new AuthenticatedUser(userId, roles) : null;
    }

}
