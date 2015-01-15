package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginPage")
public class LoginPageAction extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(req, res);
    }
}
