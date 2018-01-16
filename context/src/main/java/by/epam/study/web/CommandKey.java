package by.epam.study.web;

import java.util.Objects;

/**
 * Created by lex on 1/8/2018.
 */
public class CommandKey {

    private String method;
    private String servletPath;

    public CommandKey(String method, String servletPath) {
        this.method = Objects.requireNonNull(method);
        this.servletPath = Objects.requireNonNull(servletPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandKey that = (CommandKey) o;
        return Objects.equals(method, that.method) &&
                Objects.equals(servletPath, that.servletPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, servletPath);
    }

    @Override
    public String toString() {
        return '[' + method + ']' + servletPath;
    }

}
