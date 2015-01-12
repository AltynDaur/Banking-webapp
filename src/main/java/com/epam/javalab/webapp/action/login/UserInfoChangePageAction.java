package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoChangePageAction implements Action {
    @Override
    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User currentUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("firstName", currentUser.getName());
        req.setAttribute("password",currentUser.getPassword());
        req.setAttribute("email",currentUser.getEmail());
        req.setAttribute("role",currentUser.getRole().toString().toLowerCase());
        ActionResult result = new ActionResult("userInfoChangePage");
        return result;
    }
}
