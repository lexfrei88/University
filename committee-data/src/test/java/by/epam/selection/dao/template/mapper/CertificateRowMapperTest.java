package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;
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
public class CertificateRowMapperTest {

    private static final String SUBJECT_ID = "subject_id";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String SCORE = "score";
    private static final long SUBJECT_ID_VALUE = 3L;
    private static final long CERTIFICATE_ID_VALUE = 2L;
    private static final int SCORE_VALUE = 7;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        when(resultSet.getLong(SUBJECT_ID)).thenReturn(SUBJECT_ID_VALUE);
        when(resultSet.getString(SUBJECT_NAME)).thenReturn(SUBJECT_NAME);
        when(resultSet.getInt(SCORE)).thenReturn(SCORE_VALUE);
    }


    @Test
    public void mapRow() throws Exception {
        CertificateRowMapper rowMapper = new CertificateRowMapper();
        Certificate actual = rowMapper.mapRow(resultSet);
        actual.setId(CERTIFICATE_ID_VALUE);
        Certificate expected = new Certificate(CERTIFICATE_ID_VALUE,new Subject(SUBJECT_ID_VALUE, SUBJECT_NAME), SCORE_VALUE);
        Assert.assertEquals(expected, actual);
    }

}