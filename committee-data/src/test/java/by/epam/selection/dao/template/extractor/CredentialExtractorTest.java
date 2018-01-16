package by.epam.selection.dao.template.extractor;

import by.epam.selection.entity.Role;
import by.epam.selection.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.util.EnumSet;

import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CredentialExtractorTest {

    private static final String USER_ID = "user_id";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    private static final String PASSWORD_VALUE = "qwerty";
    private static final long USER_ID_VALUE = 6L;
    private static final String ROLE_ADMIN_STRING_VALUE = "ROLE_ADMIN";
    private static final String ROLE_USER_STRING_VALUE = "ROLE_USER";

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(resultSet.getLong(USER_ID)).thenReturn(USER_ID_VALUE);
        when(resultSet.getString(PASSWORD)).thenReturn(PASSWORD_VALUE);
        when(resultSet.getString(ROLE))
                .thenReturn(ROLE_ADMIN_STRING_VALUE)
                .thenReturn(ROLE_USER_STRING_VALUE);
    }

    @Test
    public void extractData() throws Exception {
        CredentialExtractor extractor = new CredentialExtractor();
        User actual = extractor.extractData(resultSet);
        User expected = new User(USER_ID_VALUE, PASSWORD_VALUE, EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        Assert.assertEquals(expected, actual);
    }

}