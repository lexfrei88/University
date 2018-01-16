package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lex on 12/15/2017.
 */
public class FacultyRowMapper implements RowMapper<Faculty> {

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String STUDENT_LIMIT = "student_limit";

    @Override
    public Faculty mapRow(ResultSet resultSet) throws SQLException {
        long facultyId = resultSet.getLong(FACULTY_ID);
        String facultyName = resultSet.getString(FACULTY_NAME);
        int studentLimit = resultSet.getInt(STUDENT_LIMIT);
        return new Faculty(facultyId, facultyName, studentLimit);
    }

}
