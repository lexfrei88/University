package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Faculty;
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
public class FacultyRowMapperTest {

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String STUDENT_LIMIT = "student_limit";

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        when(resultSet.getLong(FACULTY_ID)).thenReturn(2L);
        when(resultSet.getString(FACULTY_NAME)).thenReturn("faculty_name");
        when(resultSet.getInt(STUDENT_LIMIT)).thenReturn(5);
    }

    @Test
    public void mapRow() throws Exception {
        FacultyRowMapper rowMapper = new FacultyRowMapper();
        Faculty actual = rowMapper.mapRow(resultSet);
        Faculty expected = new Faculty(2L, "faculty_name", 5);
        Assert.assertEquals(expected, actual);
    }

}