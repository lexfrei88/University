package by.epam.study.web.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * @author Alex Aksionchik 12/5/2017
 * @version 0.1
 */
public class ClassPathXmlWebBeanParser {

    private static final Logger logger = LogManager.getLogger(ClassPathXmlWebBeanParser.class);

    public WebBeanDataHolder parse(String filePath) {
        WebBeanDataHolder webBeanDataHolder = null;
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (is != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(WebBeanDataHolder.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                webBeanDataHolder = (WebBeanDataHolder) jaxbUnmarshaller.unmarshal(is);
            } catch (JAXBException e) {
                logger.fatal("Error while parsing xml with web commands.\n{}",e.getMessage());
            }
        }
        return webBeanDataHolder;
    }

}
