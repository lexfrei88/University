package by.epam.study.web.view;

import java.util.Objects;

/**
 * Created by lex on 1/6/2018.
 */
public class View {

    private ActionName actionName;
    private PathConstant path;

    public View(ActionName actionName, PathConstant path) {
        this.actionName = actionName;
        this.path = path;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public PathConstant getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        View view = (View) o;
        return actionName == view.actionName &&
                Objects.equals(path, view.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionName, path);
    }

    @Override
    public String toString() {
        return actionName + ":" + path;
    }

}
