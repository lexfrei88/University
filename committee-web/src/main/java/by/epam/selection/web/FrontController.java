package by.epam.selection.web;

import by.epam.selection.exception.handler.ErrorData;
import by.epam.selection.exception.handler.ExceptionHandler;
import by.epam.selection.exception.handler.ExceptionHandlerFactory;
import by.epam.selection.util.WebUtils;
import by.epam.study.web.Command;
import by.epam.study.web.CommandKey;
import by.epam.study.web.WebContext;
import by.epam.study.web.WebContextFactory;
import by.epam.study.web.view.ActionName;
import by.epam.study.web.view.PathConstant;
import by.epam.study.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex Aksionchik 11/23/2017
 * @version 0.1
 */
public class FrontController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FrontController.class);

    private static final String WEB_COMMAND_XML_PATH_PARAMETER = "web-commands";

    private static final String JSP_PAGE_PATH_TEMPLATE = "/WEB-INF/view/%s.jsp";

    private WebContext webContext;
    private ExceptionHandler exceptionHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String webCtxXmlFilePath = getInitParameter(WEB_COMMAND_XML_PATH_PARAMETER);
        if (webCtxXmlFilePath == null) {
            throw new IllegalArgumentException("Servlet init parameter with name 'web-commands' must be specified in web.xml file.");
        } else {
            WebContextFactory factory = new WebContextFactory();
            webContext = factory.create(webCtxXmlFilePath);
        }

        ExceptionHandlerFactory exceptionHandlerFactory = new ExceptionHandlerFactory();
        exceptionHandler = exceptionHandlerFactory.create();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String servletPath = request.getServletPath();
            String method = request.getMethod();
            CommandKey key = new CommandKey(method, servletPath);
            Command command = webContext.getCommand(key);

            View view = getViewOrDefault(request, response, command);

            render(request, response, view);
        } catch (Exception e) {
            logger.error("FrontController:\n\tMessage: {}\n\tRequest URL: {}\n\tCause: {}",
                    e.getMessage(),
                    request.getRequestURL().toString(),
                    WebUtils.getExceptionRootCause(e));
            ErrorData errorData = exceptionHandler.handle(e);
            request.setAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE, errorData.getExceptionType());
            response.sendError(errorData.getHttpStatusCode(), errorData.getMessage());
        }
    }

    private View getViewOrDefault(HttpServletRequest request, HttpServletResponse response, Command command) throws ServletException {
        View view;
        if (command != null) {
            view = command.execute(request, response);
        } else {
            logger.debug("Page not found. Redirect to default page.");
            view = new View(ActionName.REDIRECT, PathConstant.PAGE_LOGIN);
        }
        return view;
    }

    private void render(HttpServletRequest request, HttpServletResponse response, View view) throws IOException, ServletException {
        PathConstant pathConstant = view.getPath();
        String path = pathConstant.toString();
        logger.debug("Page: {}", path);
        if (view.getActionName() == ActionName.REDIRECT) {
            redirect(request, response, path);
        } else {
            forward(request, response, path);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        String pathWithContext = request.getContextPath() + '/' + page;
        response.sendRedirect(pathWithContext);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        String formatPage = String.format(JSP_PAGE_PATH_TEMPLATE, page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(formatPage);
        dispatcher.forward(request, response);
    }

}
