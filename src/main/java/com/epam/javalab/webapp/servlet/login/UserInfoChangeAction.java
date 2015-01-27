package com.epam.javalab.webapp.servlet.login;

//import com.epam.javalab.webapp.dao.h2Impl.H2UserDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.security.EncryptByMD5;
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

@WebServlet("/userInfoChange")
public class UserInfoChangeAction extends HttpServlet {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        User currentUser = (User) req.getSession().getAttribute("user");
        String password = null;
        if(req.getParameter("password").isEmpty()){
            password = EncryptByMD5.encrypt(req.getParameter("oldPass"),firstName);
        } else {
            if(req.getParameter("password").equals(req.getParameter("repeatPassword"))){
                password = EncryptByMD5.encrypt(req.getParameter("password"),firstName);
            }   else {
                resp.sendRedirect("userInfoChangePage");
            }

        }
        String email = req.getParameter("email");
        String oldPass = EncryptByMD5.encrypt(req.getParameter("oldPass"),firstName);

        Role role = currentUser.getRole();
        int userID = currentUser.getId();
        if (currentUser.getPassword().equals(oldPass)) {
            currentUser.setName(firstName);
            currentUser.setPassword(password);
            currentUser.setEmail(email);

            userDAO.update(currentUser);
                req.setAttribute("message", "User successfully updated");

            req.setAttribute("message", "updateSuccess");
            resp.sendRedirect(chooseRole(role));
        } else {
            req.setAttribute("message", "errorChange");
            resp.sendRedirect(chooseRole(role));
        }

    }

    private String chooseRole(Role role) {
        if (role.equals(Role.ADMIN)) {
            return "admin/adminMainPage";
        } else {
            return "client/clientMainPage";
        }

    }
}
