package by.epam.selection.dao.template.mapper;

import by.epam.selection.entity.Certificate;
import by.epam.selection.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lex on 12/15/2017.
 */
public class CertificateRowMapper implements RowMapper<Certificate> {

    private static final String CERTIFICATE_ID = "certificate_id";
    private static final String SUBJECT_ID = "subject_id";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String SCORE = "score";

    @Override
    public Certificate mapRow(ResultSet resultSet) throws SQLException {
        Long certificateId = resultSet.getObject(CERTIFICATE_ID, Long.class);

        long subjectId = resultSet.getLong(SUBJECT_ID);
        String subjectName = resultSet.getString(SUBJECT_NAME);
        Subject subject = new Subject(subjectId, subjectName);

        int score = resultSet.getInt(SCORE);

        return new Certificate(certificateId, subject, score);
    }

}
