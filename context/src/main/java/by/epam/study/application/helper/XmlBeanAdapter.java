package by.epam.study.application.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lex on 12/12/2017.
 */
public class XmlBeanAdapter extends XmlAdapter<XmlBeanAdapter.Container, Map<String, BeanData>> {

    @Override
    public Map<String, BeanData> unmarshal(Container container) throws Exception {
        Map<String, BeanData> resultMap = new HashMap<>();
        for (Entry entry : container.entryList) {
            BeanData beanData = new BeanData();
            beanData.setClassName(entry.className);
            beanData.setInitMethod(entry.initMethod);

            List<MethodParam> methodParamsList = entry.methodParams;
            if (methodParamsList != null && !methodParamsList.isEmpty()) {
                List<String> methodParams = new ArrayList<>();
                for (MethodParam param : methodParamsList) {
                    methodParams.add(param.value);
                }
                beanData.setMethodParams(methodParams);
            }

            List<ConstructorArg> argList = entry.constructorArgs;
            if (argList != null && !argList.isEmpty()) {
                List<String> constructorArgs = new ArrayList<>();
                for (ConstructorArg arg : argList) {
                    constructorArgs.add(arg.ref);
                }
                beanData.setConstructorArgs(constructorArgs);
            }

            resultMap.put(entry.id, beanData);
        }
        return resultMap;
    }

    @Override
    public Container marshal(Map<String, BeanData> v) throws Exception {
        throw new UnsupportedOperationException();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Container {

        @XmlElement(name = "bean")
        private List<Entry> entryList = new ArrayList<>();

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Entry {

        @XmlAttribute(name = "id")
        private String id;

        @XmlAttribute(name = "class")
        private String className;

        @XmlAttribute(name = "init-method")
        private String initMethod;

        @XmlElement(name="method-param")
        private List<MethodParam> methodParams;

        @XmlElement(name = "constructor-arg")
        private List<ConstructorArg> constructorArgs;

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class ConstructorArg {

        @XmlAttribute(name = "ref")
        private String ref;

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class MethodParam {

        @XmlAttribute(name = "value")
        private String value;

    }

}
