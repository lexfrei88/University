package by.epam.selection.tablib;

import by.epam.selection.AuthenticatedUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

/**
 * Created by lex on 12/11/2017.
 */
public class AuthenticationTag extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) session.getAttribute(AuthenticatedUser.SESSION_ATTRIBUTE_NAME);
        if (authenticatedUser == null) {
            return Tag.SKIP_BODY;
        } else {
            return Tag.EVAL_BODY_INCLUDE;
        }
    }

}
