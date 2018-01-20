package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map a single row of the {@link ResultSet} to the {@link User} object
 *
 * @author Alex Aksionchik 12/15/2017
 * @version 1.0
 */
public class UserRowMapper implements RowMapper<User> {

    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String APPROVED_STATUS = "approved_status";
    private static final String FACULTY_ID = "faculty_id";

    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(USER_ID);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String email = resultSet.getString(EMAIL);
        boolean isApproved = resultSet.getBoolean(APPROVED_STATUS);
        long facultyId = resultSet.getLong(FACULTY_ID);
        Faculty faculty = new Faculty(facultyId);

        return new User(userId, firstName, lastName, email, isApproved, faculty);
    }

}
