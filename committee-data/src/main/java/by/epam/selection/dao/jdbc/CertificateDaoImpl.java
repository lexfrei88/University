package by.epam.selection.dao.jdbc;

import by.epam.selection.dao.CertificateDao;
import by.epam.selection.dao.exception.DaoException;
import by.epam.selection.dao.jdbc.template.JdbcTemplate;
import by.epam.selection.dao.jdbc.template.mapper.CertificateRowMapper;
import by.epam.selection.entity.Certificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of {@link CertificateDao} interface based on custom {@link JdbcTemplate} class
 *
 * @author Alex Aksionchik 12/5/2017
 * @version 1.0
 */
public class CertificateDaoImpl implements CertificateDao {

    private static final Logger logger = LogManager.getLogger(CertificateDaoImpl.class);

    private static final String GET_CERTIFICATES_FOR_USER_AND_FACULTY =
            "SELECT u.user_id, u.faculty_id, fs.subject_id, s.certificate_id, sub.subject_name, COALESCE(s.score, 0) AS score FROM user " +
                    "AS u INNER JOIN faculty_m2m_subject AS fs ON fs.faculty_id = u.faculty_id " +
                    "LEFT JOIN  user_certificate AS s ON s.user_id = u.user_id AND s.subject_id = fs.subject_id " +
                    "INNER JOIN subject AS sub ON sub.subject_id = fs.subject_id " +
                    "WHERE u.user_id = ?";
    private static final String INSERT_CERTIFICATE = "INSERT INTO user_certificate (user_id, subject_id, score) VALUES (?, ?, ?)";
    private static final String UPDATE_CERTIFICATE = "UPDATE user_certificate SET score = ? WHERE certificate_id = ? AND user_id = ?";

    private static final CertificateRowMapper CERTIFICATE_ROW_MAPPER = new CertificateRowMapper();

    private JdbcTemplate jdbcTemplate;

    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getAllForUser(long userId) throws DaoException {
        logger.info("Get all certificates for userId = {}.", userId);

        return jdbcTemplate.queryForList(GET_CERTIFICATES_FOR_USER_AND_FACULTY, CERTIFICATE_ROW_MAPPER, userId);
    }

    @Override
    public Certificate save(Certificate certificate, long userId) throws DaoException {
        Object[] values = {
                userId,
                certificate.getSubject().getId(),
                certificate.getScore()
        };

        Long id = jdbcTemplate.save(INSERT_CERTIFICATE, values);
        certificate.setId(id);
        return certificate;
    }

    @Override
    public Certificate update(Certificate certificate, long userId) throws DaoException {
        int score = certificate.getScore();
        Long id = certificate.getId();
        int affectedRows = jdbcTemplate.update(UPDATE_CERTIFICATE, score, id, userId);
        return affectedRows > 0 ? certificate : null;
    }

}
