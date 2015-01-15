package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.JPAImpl.JPAUserDAO;
import com.epam.javalab.webapp.dao.UserDAO;
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

@WebServlet("/login")
public class LoginAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;


    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String password = EncryptByMD5.encrypt(req.getParameter("password"), firstName);
        User currentUser = userDAO.findByNameAndPass(firstName, password);

        if (currentUser != null) {
            req.getSession().setAttribute("user", currentUser);
            Role currentRole = currentUser.getRole();
            if (currentRole.equals(Role.ADMIN)) {
                res.sendRedirect("admin/adminMainPage");

            } else if (currentRole.equals(Role.CLIENT)) {
                res.sendRedirect("client/clientMainPage");
            }

        } else {
            req.setAttribute("errorMessage", "ERROR!!!");
            req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(req, res);

        }
    }


}
