package by.epam.study.web.view;

/**
 * Created by lex on 1/6/2018.
 */
public enum ActionName {
    REDIRECT("redirect:"), FORWARD("forward:");

    private String value;

    ActionName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
