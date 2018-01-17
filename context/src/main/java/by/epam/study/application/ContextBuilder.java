package by.epam.study.application;

import by.epam.study.exception.ApplicationContextException;
import by.epam.study.application.helper.BeanData;
import by.epam.study.application.helper.BeanDataHolder;
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
        for (Map.Entry<String, BeanData> entry : beanDataHolder.getBeanDataMap().entrySet()) {
            if (contextMap.containsKey(entry.getKey())) {
                continue;
            }
            recursiveCreation(contextMap, entry.getKey(), entry.getValue(), beanDataHolder.getBeanDataMap());
        }
        return contextMap;
    }

    private void recursiveCreation(Map<String, Object> contextMap, String key, BeanData beanData, Map<String, BeanData> beanDataMap){
        if (beanData.getInitMethod() != null) {
            if (beanData.getMethodParams() == null || beanData.getMethodParams().isEmpty()) {
                instantiateByMethodWithoutParams(contextMap, key, beanData);
            } else {
                instantiateByMethodWithParams(contextMap, key, beanData);
            }
        } else if (beanData.getConstructorArgs() != null) {
            instantiateByConstructorWithParams(contextMap, key, beanData, beanDataMap);
        } else {
            instantiateByDefaultConstructor(contextMap, key, beanData);
        }
    }

    private void instantiateByMethodWithoutParams(Map<String, Object> contextMap, String key, BeanData beanData) {
        try {
            Class<?> clazz = Class.forName(beanData.getClassName());
            Method initMethod = clazz.getMethod(beanData.getInitMethod());
            Object obj = initMethod.invoke(null);
            contextMap.put(key, obj);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
    }

    private void instantiateByMethodWithParams(Map<String, Object> contextMap, String key, BeanData beanData) {
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
            Object obj = initMethod.invoke(null, args);
            contextMap.put(key, obj);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
    }

    private void instantiateByDefaultConstructor(Map<String, Object> contextMap, String key, BeanData beanData) {
        try {
            Class<?> clazz = Class.forName(beanData.getClassName());
            Object obj = clazz.newInstance();
            contextMap.put(key, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
    }

    private void instantiateByConstructorWithParams(Map<String, Object> contextMap, String key, BeanData beanData, Map<String, BeanData> beanDataMap) {
        List<String> constructorArgs = beanData.getConstructorArgs();
        try {
            int size = constructorArgs.size();
            Object[] parameters = new Object[size];
            for (int i = 0; i < size; i++) {
                String argRef = constructorArgs.get(i);
                while (!contextMap.containsKey(argRef)) {
                    BeanData emptyBeanData = beanDataMap.get(argRef);
                    recursiveCreation(contextMap, argRef, emptyBeanData, beanDataMap);
                }
                if (contextMap.containsKey(argRef)) {
                    Object bean = contextMap.get(argRef);
                    parameters[i] = bean;
                    Class<?> clazz = Class.forName(beanData.getClassName());
                    Object newBean = ConstructorUtils.invokeConstructor(clazz, parameters);
                    contextMap.put(key, newBean);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ApplicationContextException(e.getMessage(), e);
        }
    }

}
