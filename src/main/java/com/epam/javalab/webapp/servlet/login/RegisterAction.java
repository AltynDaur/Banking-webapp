package com.epam.javalab.webapp.servlet.login;

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

@WebServlet("/register")
public class RegisterAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String password = req.getParameter("password");
        String repeatPass = req.getParameter("repeatPass");
        String email = req.getParameter("email");
        if(password.equals(repeatPass)){

            password = EncryptByMD5.encrypt(password, firstName);
            User currentUser = new User(firstName, password, email, Role.CLIENT);

            userDAO.add(currentUser);

            req.setAttribute("message", "User successfully registered");
        } else {
            req.setAttribute("message", "Database problems");
        }
        resp.sendRedirect("loginPage");


    }


}
