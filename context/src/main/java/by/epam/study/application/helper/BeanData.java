package by.epam.study.application.helper;

import java.util.List;

/**
 * Created by lex on 12/12/2017.
 */
public class BeanData {

    private String className;
    private String initMethod;
    private List<String> methodParams;
    private List<String> constructorArgs;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public List<String> getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(List<String> methodParams) {
        this.methodParams = methodParams;
    }

    public List<String> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<String> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

}
