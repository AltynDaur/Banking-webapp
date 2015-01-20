package com.epam.javalab.webapp.servlet.login;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.servlet.ActionResult;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
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
        String name = req.getParameter("firstName");
        String password = EncryptByMD5.encrypt(req.getParameter("password"), name);
        User currentUser = null;
        try {
            currentUser = userDAO.findByNameAndPass(name, password);
        } catch (NoResultException e) {

            req.setAttribute("message", "Current User not found!");
            req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(req, res);
        }

        if (currentUser != null) {
            req.getSession().setAttribute("user", currentUser);
            Role currentRole = currentUser.getRole();
            if (currentRole.equals(Role.ADMIN)) {
                res.sendRedirect("admin/adminMainPage");
            } else if (currentRole.equals(Role.CLIENT)) {
                res.sendRedirect("client/clientMainPage");
            }

        } else {
            req.setAttribute("message", "ERROR!!!");
            req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(req, res);

        }
    }


}
