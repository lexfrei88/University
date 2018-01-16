package by.epam.study.web.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lex on 12/15/2017.
 */
@XmlRootElement(name = "commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebBeanDataHolder {

    @XmlElement(name = "command")
    private List<WebBeanData> webBeanDataList = new ArrayList<>();

    public List<WebBeanData> getWebBeanDataList() {
        return webBeanDataList;
    }

}
