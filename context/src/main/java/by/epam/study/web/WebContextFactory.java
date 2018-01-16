package by.epam.study.web;

import by.epam.study.web.helper.ClassPathXmlWebBeanParser;
import by.epam.study.web.helper.WebBeanDataHolder;

/**
 * Created by lex on 12/15/2017.
 */
public class WebContextFactory {

    public WebContext create(String filePath) {
        ClassPathXmlWebBeanParser classPathXmlWebBeanParser = new ClassPathXmlWebBeanParser();
        WebBeanDataHolder webBeanDataHolder = classPathXmlWebBeanParser.parse(filePath);
        WebContextBuilder webContextBuilder = new WebContextBuilder();
        return webContextBuilder.build(webBeanDataHolder);
    }

}
