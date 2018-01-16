package by.epam.study.web;

import by.epam.study.web.view.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex Aksionchik 11/30/2017
 * @version 0.1
 */
public interface Command {
    String REDIRECT = "redirect:";

    View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
