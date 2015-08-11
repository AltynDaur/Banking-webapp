package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterAction implements Action {

    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String password = req.getParameter("password");
        String repeatPass = req.getParameter("repeatPass");
        String email = req.getParameter("email");
        ActionResult result = new ActionResult();
        Locale currentLocale = req.getLocale();
        ResourceBundle text = ResourceBundle.getBundle("text",currentLocale);

        if(password.equals(repeatPass)){
            H2UserDAO h2UserDAO = new H2UserDAO();
            password = EncryptByMD5.encrypt(password,firstName);
            h2UserDAO.add(firstName, password, Role.CLIENT, email);
            result.setPath("loginPage");
            result.setRedirect(true);
        } else {

            req.setAttribute("message",text.getString("registerError"));
            result.setPath("loginPage");
        }
        return result;
    }
}
