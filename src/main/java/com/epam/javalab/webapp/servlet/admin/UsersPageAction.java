package com.epam.javalab.webapp.servlet.admin;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/usersPage")
public class UsersPageAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("add")){

            List<Role> roles = new ArrayList();
            for (int i = 0; i < Role.values().length; i++) {
                roles.add(Role.values()[i]);
            }
            req.setAttribute("roles", roles);
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            int userID = Integer.parseInt(req.getParameter("userID"));
            User targetUser = null;
            try {
                targetUser = userDAO.findUserByID(userID);
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/users");
            }
            req.setAttribute("user", targetUser);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/usersPage.jsp").forward(req, resp);
    }

}
