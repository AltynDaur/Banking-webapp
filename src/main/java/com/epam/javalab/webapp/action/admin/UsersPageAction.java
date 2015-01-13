package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*String action = req.getParameter("action");
        ActionResult result = new ActionResult();
        if(action.equals("add")){
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            int accID = Integer.parseInt(req.getParameter("userID"));
            UserDAO h2UserDAO = new H2UserDAO();
            User targetUser = h2UserDAO.findUserByID(accID);
            req.setAttribute("userID",targetUser.getId());
            req.setAttribute("firstName",targetUser.getName());
            req.setAttribute("password",targetUser.getPassword());
            req.setAttribute("email",targetUser.getEmail());
            req.setAttribute("role",targetUser.getRole());
            req.setAttribute("currentPass", targetUser.getPassword());
        }
        result.setPath("admin/usersPage");
        return result;*/
        return null;
    }
}
