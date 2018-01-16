package by.epam.study.application;

/**
 * Created by lex on 12/12/2017.
 */
public interface ApplicationContext {
    Object getBean(String beanName);

    <T> T getBean(Class<T> clazz);
}
