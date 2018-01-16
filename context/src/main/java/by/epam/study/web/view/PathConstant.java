package by.epam.study.web.view;

/**
 * Created by lex on 1/6/2018.
 */
public enum PathConstant {
    PAGE_LOGIN("login"),
    PAGE_FACULTY("faculty"),
    PAGE_REGISTER("register"),
    PAGE_ADMIN_SELECTION("admin/select"),
    PAGE_ADMIN_MENU("admin/menu"),
    AJAX_CERTIFICATE_POST("fragment/ajaxCertificatePostResponse"),

    COMMAND_LOGIN("login"),
    COMMAND_FACULTY("user/faculty"),
    COMMAND_ADMIN_APPROVE("admin/approve");

    private String value;

    PathConstant(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
