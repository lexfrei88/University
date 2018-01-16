package by.epam.study.application.helper;

import by.epam.study.exception.ApplicationContextException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lex on 12/12/2017.
 */
public class ClassPathXmlBeanParser {


    private static ClassPathXmlBeanParser instance;

    private ClassPathXmlBeanParser() {
    }

    public static ClassPathXmlBeanParser getInstance() {
        if (instance == null) {
            instance = new ClassPathXmlBeanParser();
        }
        return instance;
    }

    public BeanDataHolder parse(String fileName) {
        BeanDataHolder beanDataHolder = null;
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanDataHolder.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            beanDataHolder = (BeanDataHolder) unmarshaller.unmarshal(is);
        } catch (JAXBException | IOException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
        return beanDataHolder;
    }

}
