package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.template.JdbcTemplate;
import by.epam.selection.dao.template.extractor.CredentialExtractor;
import by.epam.selection.dao.template.mapper.UserRowMapper;
import by.epam.selection.entity.Role;
import by.epam.selection.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String GET_USER = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.approved_status, u.faculty_id, " +
            "ur.role FROM user AS u INNER JOIN user_role AS ur ON u.user_id = ur.user_id WHERE u.user_id = ?";

    private static final String GET_USER_BY_EMAIL = "SELECT u.user_id, u.password, ur.role FROM user AS u INNER JOIN user_role AS ur " +
            "ON u.user_id = ur.user_id WHERE email = ?";

    private static final String GET_ALL_UNAPPROVED_USER = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.password, " +
            "u.approved_status, u.faculty_id, f.faculty_name FROM user AS u INNER JOIN faculty AS f ON f.faculty_id = u.faculty_id WHERE " +
            "u.approved_status = '0'";

    private static final String FINAL_SELECT = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.approved_status, u.faculty_id, " +
            "SUM(COALESCE(s.score, 0)) AS sum_score FROM user AS u INNER JOIN faculty_m2m_subject AS fs ON fs.faculty_id = u.faculty_id " +
            "LEFT JOIN user_certificate AS s ON s.user_id = u.user_id AND s.subject_id = fs.subject_id LEFT JOIN subject AS sub ON " +
            "sub.subject_id = fs.subject_id WHERE u.approved_status = 1 AND u.faculty_id = ? GROUP BY u.user_id ORDER BY sum_score DESC " +
            "LIMIT ?";

    private static final String INSERT_USER = "INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, MD5(?))";
    private static final String INSERT_ROLE = "INSERT INTO user_role (user_id, role) VALUES (?, ?)";
    private static final String UPDATE_FACULTY = "UPDATE user SET faculty_id = ? WHERE user_id = ?";
    private static final String UPDATE_APPROVE_STATUS = "UPDATE user SET approved_status = b'1' WHERE user_id = ?";

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final CredentialExtractor CREDENTIAL_EXTRACTOR = new CredentialExtractor();

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) throws DaoException {
        logger.info("Save new User.");

        Object[] values = {
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        };

        Long userId = jdbcTemplate.save(INSERT_USER, values);
        if (userId != null) {
            user.setId(userId);
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                jdbcTemplate.save(INSERT_ROLE, userId, role.toString());
            }
        }
        return user;
    }

    @Override
    public User get(long id) throws DaoException {
        logger.info("Get user by id = {}", id);

        return jdbcTemplate.queryForObject(GET_USER, USER_ROW_MAPPER, id);
    }

    @Override
    public User getByEmail(String email) throws DaoException {
        logger.info("Get user by email = {}", email);

        return jdbcTemplate.query(GET_USER_BY_EMAIL, CREDENTIAL_EXTRACTOR, email);
    }

    @Override
    public boolean updateFaculty(long facultyId, long userId) throws DaoException {
        logger.info("For userId = {}, set facultyId = {}.", userId, facultyId);

        return jdbcTemplate.update(UPDATE_FACULTY, facultyId, userId) > 0;
    }

    @Override
    public List<User> getAllUnapproved() throws DaoException {
        logger.info("Get all users who was not approved by admin.");

        return jdbcTemplate.queryForList(GET_ALL_UNAPPROVED_USER, USER_ROW_MAPPER);
    }

    @Override
    public boolean updateApproveStatus(long userId) throws DaoException {
        logger.info("Update approve status for userId = {}", userId);

        return jdbcTemplate.update(UPDATE_APPROVE_STATUS, userId) > 0;
    }

    public List<User> getAllSelected(long facultyId, int studentLimit) throws DaoException {
        logger.info("Select all new student for faculty.");

        return jdbcTemplate.queryForList(FINAL_SELECT, USER_ROW_MAPPER, facultyId, studentLimit);
    }

}
