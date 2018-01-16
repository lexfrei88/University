package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Faculty;
import by.epam.selection.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;

import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRowMapperTest {

    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String APPROVED_STATUS = "approved_status";
    private static final String FACULTY_ID = "faculty_id";

    @Mock
    private ResultSet resultSet;


    @Before
    public void setUp() throws Exception {
        when(resultSet.getLong(USER_ID)).thenReturn(1L);
        when(resultSet.getString(FIRST_NAME)).thenReturn("first_name");
        when(resultSet.getString(LAST_NAME)).thenReturn("last_name");
        when(resultSet.getString(EMAIL)).thenReturn("user@mail.com");
        when(resultSet.getBoolean(APPROVED_STATUS)).thenReturn(false);
        when(resultSet.getLong(FACULTY_ID)).thenReturn(7L);
    }

    @Test
    public void mapRow() throws Exception {
        UserRowMapper rowMapper = new UserRowMapper();
        User actual = rowMapper.mapRow(resultSet);
        User expected = new User(1L, "first_name", "last_name", "user@mail.com", false, new Faculty(7L));
        Assert.assertEquals(expected, actual);
    }

}