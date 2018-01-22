package by.epam.selection.util;

import by.epam.selection.exception.WrongParameterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class for Web Layer
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public final class WebUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private WebUtils() {
    }

    /**
     * Check either passed parameter not {@code null} or empty
     *
     * @param parameter     - parameter value should be checked
     * @param parameterName - parameter name
     * @return parameter value itself if it's correct else throw exception
     * @throws WrongParameterException if parameter is incorrect
     */
    public static String requiredNotEmptyParameter(String parameter, String parameterName) throws WrongParameterException {
        if (parameter == null || parameter.isEmpty()) {
            throw new WrongParameterException(parameterName + " cannot be null or empty.");
        }
        return parameter;
    }

    /**
     * Check either array of passed parameters is a number
     *
     * @param parameterValues - parameter values that should be checked
     * @param parameterName   - parameter name
     * @return parameter value itself if it's correct else throw exception
     * @throws WrongParameterException if parameter is incorrect
     */
    public static String[] requiredNumberParameter(String[] parameterValues, String parameterName) throws WrongParameterException {
        if (parameterValues == null) {
            throw new WrongParameterException(parameterName + " cannot be null.");
        }
        for (int i = 0; i < parameterValues.length; i++) {
            requiredNumberParameter(parameterValues[i], parameterName + " No." + i);
        }
        return parameterValues;
    }

    /**
     * Check either passed parameter is a number
     *
     * @param parameter - parameter value that should be checked
     * @param parameterName   - parameter name
     * @return parameter value itself if it's correct else throw exception
     * @throws WrongParameterException if parameter is incorrect
     */
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

    /**
     * Get root cause of the passed exception
     *
     * @param e - exception which root cause should be retrieved
     * @return root cause represented by {@link Throwable} object
     */
    public static Throwable getExceptionRootCause(Exception e) {
        Throwable cause;
        Throwable result = e;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

}
