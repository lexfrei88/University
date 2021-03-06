package by.epam.selection.dao.jdbc.template.extractor;

import by.epam.selection.entity.Role;
import by.epam.selection.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Extract credential data for {@link User} entity such as ID, Password and Roles.
 *
 * @author Alex Aksionchik 12/27/2017
 * @version 1.0
 */
public class CredentialExtractor implements ResultSetExtractor<User> {

    private static final String USER_ID = "user_id";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    @Override
    public User extractData(ResultSet resultSet) throws SQLException {
        Long id = null;
        String password = null;
        Set<Role> roles = EnumSet.noneOf(Role.class);
        while (resultSet.next()) {
            if (id == null) {
                id = resultSet.getLong(USER_ID);
            }
            if (password == null) {
                password = resultSet.getString(PASSWORD);
            }
            String roleString = resultSet.getString(ROLE);
            Role role = Role.valueOf(roleString.toUpperCase());
            roles.add(role);
        }
        return id == null ? null : new User(id, password, roles);
    }

}
