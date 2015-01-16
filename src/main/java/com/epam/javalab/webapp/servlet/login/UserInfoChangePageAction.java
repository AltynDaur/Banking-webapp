package com.epam.javalab.webapp.servlet.login;

import com.epam.javalab.webapp.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userInfoChangePage")
public class UserInfoChangePageAction extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("firstName", currentUser.getName());
        req.setAttribute("password",currentUser.getPassword());
        req.setAttribute("email",currentUser.getEmail());
        req.setAttribute("role", currentUser.getRole().toString().toUpperCase());
        req.getRequestDispatcher("/WEB-INF/jsp/userInfoChangePage.jsp").forward(req, resp);
    }

}
