package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeLocale")
public class ChangeLocaleAction extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleAction.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("language", req.getParameter("language"));
        String path = req.getHeader("Referer").substring(30);
        resp.sendRedirect(path);
    }


}
