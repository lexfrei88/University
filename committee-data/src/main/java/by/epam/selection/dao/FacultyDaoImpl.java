package by.epam.selection.dao;

import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.template.JdbcTemplate;
import by.epam.selection.dao.template.mapper.FacultyRowMapper;
import by.epam.selection.entity.Faculty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of {@link FacultyDao} interface based on custom {@link JdbcTemplate} class
 *
 * @author Alex Aksionchik 12/9/2017
 * @version 1.0
 */
public class FacultyDaoImpl implements FacultyDao {

    private static final Logger logger = LogManager.getLogger(FacultyDaoImpl.class);

    private static final String GET_ALL = "SELECT faculty_id, faculty_name, student_limit FROM faculty";
    private static final String GET_BY_ID = "SELECT faculty_id, faculty_name, student_limit FROM faculty WHERE faculty_id = ?";

    private static final FacultyRowMapper FACULTY_ROW_MAPPER = new FacultyRowMapper();

    private JdbcTemplate jdbcTemplate;

    public FacultyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Faculty> getAll() throws DaoException {
        logger.info("Get all faculties.");

        return jdbcTemplate.queryForList(GET_ALL, FACULTY_ROW_MAPPER);
    }

    @Override
    public Faculty get(long facultyId) throws DaoException {
        logger.info("Get faculty by id.");

        return jdbcTemplate.queryForObject(GET_BY_ID, FACULTY_ROW_MAPPER, facultyId);
    }

}
