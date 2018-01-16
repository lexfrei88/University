package by.epam.selection.dao.template;

import by.epam.selection.dao.connection.ConnectionHolder;
import by.epam.selection.dao.template.extractor.ResultSetExtractor;
import by.epam.selection.dao.util.DaoUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;


/**
 * Created by lex on 1/5/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoUtils.class)
@PowerMockIgnore("javax.management.*")
public class JdbcTemplateTest {

    private static final String DATA = "data";

    private static final String INSERT_QUERY = "INSERT INTO table (id) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE table SET id = ?";
    private static final String SELECT_QUERY = "SELECT 1 FROM table WHERE id = ?";

    @Mock private ConnectionHolder connectionHolder;
    @Mock private Connection connection;
    @Mock private PreparedStatement preparedStatement;
    @Mock private ResultSet resultSet;
    @Mock private ResultSetExtractor<String> resultSetExtractor;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        jdbcTemplate = new JdbcTemplate(connectionHolder);

        PowerMockito.mockStatic(DaoUtils.class);
        when(DaoUtils.prepareStatementWithId(any(), anyString(), any())).thenReturn(preparedStatement);
        when(DaoUtils.prepareStatement(any(), anyString(), any())).thenReturn(preparedStatement);

        when(connectionHolder.getConnection()).thenReturn(connection);

        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong(1)).thenReturn(1L);

        when(connection.getAutoCommit()).thenReturn(true);

        when(resultSetExtractor.extractData(any())).thenReturn(DATA);
    }

    @Test
    public void save() throws Exception {
        int id = 1;
        Long actual = jdbcTemplate.save(INSERT_QUERY, id);
        Assert.assertEquals(1L, (long) actual);
    }

    @Test
    public void update() throws Exception {
        int id = 1;
        int actual = jdbcTemplate.update(UPDATE_QUERY, id);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void query() throws Exception {
        int id = 1;
        Object actual = jdbcTemplate.query(SELECT_QUERY, resultSetExtractor, id);
        Assert.assertEquals(DATA, actual);
    }

}