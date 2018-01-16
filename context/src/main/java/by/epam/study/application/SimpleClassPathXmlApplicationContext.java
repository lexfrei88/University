package by.epam.study.application;

import by.epam.study.application.helper.BeanDataHolder;
import by.epam.study.application.helper.ClassPathXmlBeanParser;

import java.util.Map;

/**
 * Created by lex on 12/12/2017.
 */
public class SimpleClassPathXmlApplicationContext implements ApplicationContext {

    private static final String DEFAULT_APP_CONTEXT_XML_FILE_PATH = "app-context.xml";

    private static SimpleClassPathXmlApplicationContext instance;

    private Map<String, Object> context;

    private SimpleClassPathXmlApplicationContext(Map<String, Object> context) {
        this.context = context;
    }

    public static SimpleClassPathXmlApplicationContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Context is not initialized.");
        }
        return instance;
    }

    public static void initialize(String filePath) {
        if (instance != null) {
            throw new IllegalStateException("Context already existed. Use getInstance() method to get it.");
        }
        ClassPathXmlBeanParser beanParser = ClassPathXmlBeanParser.getInstance();
        BeanDataHolder beanDataHolder = beanParser.parse(filePath);
        ContextBuilder contextBuilder = ContextBuilder.getInstance();
        Map<String, Object> context = contextBuilder.build(beanDataHolder);
        instance = new SimpleClassPathXmlApplicationContext(context);
    }

    public static void initialize() {
        initialize(DEFAULT_APP_CONTEXT_XML_FILE_PATH);
    }

    @Override
    public Object getBean(String beanName) {
        return context.get(beanName);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return context.values().stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .findFirst()
                .orElse(null);
    }

}
