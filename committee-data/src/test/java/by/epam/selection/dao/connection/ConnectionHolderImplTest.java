package by.epam.selection.dao.connection;

import by.epam.selection.dao.connection.holder.ConnectionHolderImpl;
import by.epam.selection.dao.connection.pool.ConnectionPool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by lex on 1/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectionHolderImplTest {

    @Mock
    private Connection connection;

    @Mock
    private ConnectionPool connectionPool;

    private ConnectionHolderImpl connectionHolder;

    @Before
    public void init() {
        when(connectionPool.getConnection()).thenReturn(connection);
        connectionHolder = new ConnectionHolderImpl(connectionPool);
    }

    @Test
    public void shouldNotNullConnectionWhenGetConnection() {
        Connection connection = connectionHolder.getConnection();
        verify(connectionPool).getConnection();
        verifyNoMoreInteractions(connectionPool);
        Assert.assertNotNull(connection);
    }

    @Test
    public void shouldConnectionPoolCloseWhenClose() throws Exception {
        connectionHolder.close();
        verify(connectionPool).close(any());
    }

    @Test
    public void shouldConnectionWithAutoCommitFalseWhenGetTxConnection() throws Exception {
        connectionHolder.getTxConnection();
        verify(connection).setAutoCommit(false);
    }

    @Test
    public void shouldConnectionCommitWhenCommitTx() throws Exception {
        connectionHolder.commitTx();
        verify(connection).commit();
    }

    @Test
    public void shouldConnectionRollbackWhenRollbackTx() throws Exception {
        connectionHolder.rollbackTx();
        verify(connection).rollback();
    }

}
