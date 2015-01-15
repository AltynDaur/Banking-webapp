package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class UserDeleteAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        try {
            userDAO.delete(user);
            req.setAttribute("message", "User successfully deleted");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/users");
        }
        resp.sendRedirect("admin/users");
    }

}
