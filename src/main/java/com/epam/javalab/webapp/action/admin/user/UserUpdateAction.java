package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
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
        H2UserDAO h2UserDAO = new H2UserDAO();
        h2UserDAO.update(userName,password,email,role,userID);
        return new ActionResult("admin/users", true);
    }
}
