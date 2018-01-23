package by.epam.study.application;

import by.epam.study.application.helper.BeanData;
import by.epam.study.application.helper.BeanDataHolder;
import by.epam.study.exception.ApplicationContextException;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lex on 12/12/2017.
 */
public class ContextBuilder {

    private static final ContextBuilder instance = new ContextBuilder();

    private ContextBuilder() {
    }

    public static ContextBuilder getInstance() {
        return instance;
    }

    public Map<String, Object> build(BeanDataHolder beanDataHolder) {
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, BeanData> beanDataMap = beanDataHolder.getBeanDataMap();
        for (Map.Entry<String, BeanData> entry : beanDataMap.entrySet()) {
            if (contextMap.containsKey(entry.getKey())) {
                continue;
            }
            String key = entry.getKey();
            BeanData value = entry.getValue();
            putBeanInContext(beanDataMap, contextMap, key, value);
        }
        return contextMap;
    }

    private void putBeanInContext(Map<String, BeanData> beanDataMap, Map<String, Object> contextMap, String key, BeanData value) {
        Object bean = beanInstantiation(contextMap, value, beanDataMap);
        contextMap.put(key, bean);
    }

    private Object beanInstantiation(Map<String, Object> contextMap, BeanData beanData, Map<String, BeanData> beanDataMap){
        Object bean;
        if (beanData.getInitMethod() != null) {
            if (beanData.getMethodParams() == null || beanData.getMethodParams().isEmpty()) {
                bean = instantiateByMethodWithoutParams(beanData);
            } else {
                bean = instantiateByMethodWithParams(beanData);
            }
        } else if (beanData.getConstructorArgs() != null) {
            bean = instantiateByConstructorWithParams(contextMap, beanData, beanDataMap);
        } else {
            bean = instantiateByDefaultConstructor(beanData);
        }
        return bean;
    }

    private Object instantiateByMethodWithoutParams(BeanData beanData) {
        Object bean;
        try {
            Class<?> clazz = Class.forName(beanData.getClassName());
            Method initMethod = clazz.getMethod(beanData.getInitMethod());
            bean = initMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
        return bean;
    }

    private Object instantiateByMethodWithParams(BeanData beanData) {
        Object bean;
        try {
            Class<?> clazz = Class.forName(beanData.getClassName());
            List<Class<String>> paramsTypesList = new ArrayList<>();
            for (int i = 0; i < beanData.getMethodParams().size(); i++) {
                paramsTypesList.add(String.class);
            }
            Class[] paramsTypesArray = paramsTypesList.toArray(new Class[paramsTypesList.size()]);
            Method initMethod = clazz.getMethod(beanData.getInitMethod(), paramsTypesArray);
            List<String> methodParamsList = beanData.getMethodParams();
            Object[] args = methodParamsList.toArray(new String[methodParamsList.size()]);
            bean = initMethod.invoke(null, args);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
        return bean;
    }

    private Object instantiateByDefaultConstructor(BeanData beanData) {
        Object bean;
        try {
            Class<?> clazz = Class.forName(beanData.getClassName());
            bean = clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
        return bean;
    }

    private Object instantiateByConstructorWithParams(Map<String, Object> contextMap, BeanData beanData, Map<String, BeanData> beanDataMap) {
        Object newBean = null;
        List<String> constructorArgs = beanData.getConstructorArgs();
        try {
            int size = constructorArgs.size();
            Object[] parameters = new Object[size];
            for (int i = 0; i < size; i++) {
                String argRef = constructorArgs.get(i);
                while (!contextMap.containsKey(argRef)) {
                    BeanData emptyBeanData = beanDataMap.get(argRef);
                    putBeanInContext(beanDataMap, contextMap, argRef, emptyBeanData);
                }
                Object bean = contextMap.get(argRef);
                parameters[i] = bean;
                Class<?> clazz = Class.forName(beanData.getClassName());
                newBean = ConstructorUtils.invokeConstructor(clazz, parameters);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
        return newBean;
    }

}
