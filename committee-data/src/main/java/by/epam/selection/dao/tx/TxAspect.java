package by.epam.selection.dao.tx;

import by.epam.selection.dao.connection.Transactional;
import by.epam.selection.dao.exception.DaoException;
import by.epam.study.application.ApplicationContext;
import by.epam.study.application.SimpleClassPathXmlApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.sql.SQLException;

/**
 * Created by lex on 12/15/2017.
 */
@Aspect
public class TxAspect {

    private static final Logger logger = LogManager.getLogger(TxAspect.class);

    @Pointcut("@annotation(by.epam.selection.dao.tx.Transaction)")
    public void tx() {}

    @Around("tx()")
    public Object transaction(ProceedingJoinPoint pjp) throws DaoException {
        logger.info("Begin tx.");

        ApplicationContext context = SimpleClassPathXmlApplicationContext.getInstance();
        Transactional transactional = context.getBean(Transactional.class);
        try {
            transactional.getTxConnection();
            pjp.proceed();
            logger.debug("Tx commit.");
            transactional.commitTx();
        } catch (Throwable throwable) {
            logger.debug("Tx rollback.");

            transactional.rollbackTx();
            throw new DaoException(throwable);
        } finally {
            logger.debug("Connection close.");
            try {
                transactional.close();
            } catch (SQLException e) {
                logger.error("Exception while closing connection occur.");
            }
        }
        logger.info("Tx end.");
        return null;
    }

}
