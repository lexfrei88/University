package by.epam.study.application.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lex on 12/12/2017.
 */
@XmlRootElement(name = "context")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanDataHolder {

    @XmlElement(name = "beans")
    @XmlJavaTypeAdapter(XmlBeanAdapter.class)
    private Map<String, BeanData> beanMap = new HashMap<>();

    public Map<String, BeanData> getBeanDataMap() {
        return beanMap;
    }

}
