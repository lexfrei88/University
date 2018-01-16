package by.epam.study.web.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by lex on 12/15/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WebBeanData {

    @XmlAttribute
    private String method;

    @XmlElement
    private String path;

    @XmlElement
    private String className;

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getClassName() {
        return className;
    }

}
