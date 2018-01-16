package by.epam.selection.util;

import by.epam.selection.exception.WrongParameterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lex on 1/7/2018.
 */
public final class WebUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private WebUtils() {
    }

    public static String requiredNotEmptyParameter(String parameter, String parameterName) throws WrongParameterException {
        if (parameter == null || parameter.isEmpty()) {
            throw new WrongParameterException(parameterName + " cannot be null or empty.");
        }
        return parameter;
    }

    public static String[] requiredNumberParameter(String[] parameterValues, String parameterName) throws WrongParameterException {
        if (parameterValues == null) {
            throw new WrongParameterException(parameterName + " cannot be null.");
        }
        for (int i = 0; i < parameterValues.length; i++) {
            requiredNumberParameter(parameterValues[i], parameterName + " No." + i);
        }
        return parameterValues;
    }

    public static String requiredNumberParameter(String parameter, String parameterName) throws WrongParameterException {
        if (parameter == null) {
            throw new WrongParameterException(parameterName + " cannot be null.");
        }
        Matcher matcher = NUMBER_PATTERN.matcher(parameter);
        if (!matcher.matches()) {
            throw new WrongParameterException(parameterName + " should be a number.");
        }
        return parameter;
    }

    public static Throwable getExceptionRootCause(Exception e) {
        Throwable cause;
        Throwable result = e;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

}
