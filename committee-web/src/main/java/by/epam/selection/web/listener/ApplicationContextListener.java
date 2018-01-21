package by.epam.selection.web.listener;

import by.epam.selection.dao.connection.ConnectionPool;
import by.epam.study.application.ApplicationContext;
import by.epam.study.application.SimpleClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener that do all necessary preparations during start up and shut down application
 *
 * @author Alex Aksionchik 12/13/2017
 * @version 1.0
 */
public class ApplicationContextListener implements ServletContextListener {

    private static final String APP_CONTEXT_PATH_PARAMETER = "app-context";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String appCtxXmlFilePath = sce.getServletContext().getInitParameter(APP_CONTEXT_PATH_PARAMETER);
        if (appCtxXmlFilePath == null) {
            SimpleClassPathXmlApplicationContext.initialize();
        } else {
            SimpleClassPathXmlApplicationContext.initialize(appCtxXmlFilePath);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ApplicationContext applicationContext = SimpleClassPathXmlApplicationContext.getInstance();
        ConnectionPool connectionPool = applicationContext.getBean(ConnectionPool.class);
        connectionPool.destroy();
    }

}
