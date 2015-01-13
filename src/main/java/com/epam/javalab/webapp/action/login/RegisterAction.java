package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
//import com.epam.javalab.webapp.dao.h2Impl.H2UserDAO;
import com.epam.javalab.webapp.dao.JPAImpl.JPAUserDAO;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterAction implements Action {
    @Override
    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String password = req.getParameter("password");
        String repeatPass = req.getParameter("repeatPass");
        String email = req.getParameter("email");
        ActionResult result = new ActionResult();
        Locale currentLocale = req.getLocale();
        ResourceBundle text = ResourceBundle.getBundle("text",currentLocale);

        if(password.equals(repeatPass)){

            password = EncryptByMD5.encrypt(password,firstName);
            User currentUser = new User(firstName, password, email, Role.CLIENT);
            UserDAO userDAO = new JPAUserDAO();
            userDAO.add(currentUser);
            result.setPath("loginPage");
            result.setRedirect(true);
        } else {

            req.setAttribute("message",text.getString("registerError"));
            result.setPath("loginPage");
        }
        return result;
    }
}
