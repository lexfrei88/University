package by.epam.selection.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Util class for Web Layer
 *
 * @author Alex Aksionchik 1/7/2018
 * @version 1.0
 */
public final class WebUtils {

    private static final String LOCALE_ATTR = "lang";
    private static final String DEFAULT_LOCALE = "en";

    private WebUtils() {
    }

    /**
     * Check either passed parameter not {@code null} or empty
     *
     * @param parameter     - parameter value should be checked
     * @param parameterName - parameter name
     * @param bundle        - bundle with internationalized messages
     * @param errorMessage  - map that collects error messages
     */
    public static void notEmpty(String parameter, String parameterName, ResourceBundle bundle, Map<String, String> errorMessage) {
        if (parameter == null || parameter.trim().isEmpty()) {
            String message = bundle.getString(parameterName + ".error");
            errorMessage.put(parameterName, message);
        }
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


    public static Locale getLocale(HttpServletRequest request) {
        String localeValue = (String) request.getSession().getAttribute(LOCALE_ATTR);
        if (localeValue == null || localeValue.trim().isEmpty()) {
            localeValue = DEFAULT_LOCALE;
        }
        return new Locale(localeValue);
    }

}
