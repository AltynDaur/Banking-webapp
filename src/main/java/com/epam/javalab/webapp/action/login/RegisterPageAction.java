package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerPage")
public class RegisterPageAction extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registerPage.jsp").forward(req, resp);
    }
}
