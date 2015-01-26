package com.epam.javalab.webapp.servlet.admin.user;


import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/updateUser")
public class UserUpdateAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = null;
        if(req.getParameter("password").equals(req.getParameter("currentPass"))){
            password = req.getParameter("password");
        } else {
            password = EncryptByMD5.encrypt(req.getParameter("password"),userName);
        }

        String email = req.getParameter("email");
        int userID = Integer.parseInt(req.getParameter("userID"));
        Role role = Role.valueOf(req.getParameter("role").toUpperCase());
        User user = new User(userName, password, email, role);
        user.setId(userID);

            userDAO.update(user);
            req.setAttribute("message", "User successfully updated");

        resp.sendRedirect("users");
    }

}
