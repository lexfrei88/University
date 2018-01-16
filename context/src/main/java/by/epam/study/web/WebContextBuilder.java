package by.epam.study.web;

import by.epam.study.annotation.SimpleAutowire;
import by.epam.study.application.ApplicationContext;
import by.epam.study.application.SimpleClassPathXmlApplicationContext;
import by.epam.study.web.helper.WebBeanData;
import by.epam.study.web.helper.WebBeanDataHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lex on 12/15/2017.
 */
public class WebContextBuilder {

    private static final Logger logger = LogManager.getLogger(WebContextBuilder.class);

    public WebContext build(WebBeanDataHolder webBeanDataHolder) {
        Map<CommandKey, Command> resultMap = new HashMap<>();
        ApplicationContext appCtx = SimpleClassPathXmlApplicationContext.getInstance();
        for (WebBeanData webBeanData : webBeanDataHolder.getWebBeanDataList()) {
            try {
                Class<?> clazz = Class.forName(webBeanData.getClassName());
                Command command = (Command) clazz.newInstance();
                for(Field field  : clazz.getDeclaredFields())
                {
                    if (field.isAnnotationPresent(SimpleAutowire.class))
                    {
                        Class fieldType = field.getType();
                        Object bean = appCtx.getBean(fieldType);
                        field.setAccessible(true);
                        field.set(command, bean);
                    }
                }
                CommandKey key = new CommandKey(webBeanData.getMethod(), webBeanData.getPath());
                resultMap.put(key, command);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                logger.warn("Class {} for command {} was not crated.", webBeanData.getClassName(), webBeanData.getMethod() + ":" + webBeanData.getPath());
            }
        }
        return new WebContext(resultMap);
    }

}
