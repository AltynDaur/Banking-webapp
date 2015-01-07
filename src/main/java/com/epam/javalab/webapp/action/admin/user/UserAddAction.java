package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAddAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String password = EncryptByMD5.encrypt(req.getParameter("password"),userName);
        String email = req.getParameter("email");
        Role role = Role.valueOf(req.getParameter("role").toUpperCase());
        H2UserDAO h2UserDAO = new H2UserDAO();
        h2UserDAO.add(userName,password,role,email);
        ActionResult result = new ActionResult("admin/users",true);
        return result;
    }
}
