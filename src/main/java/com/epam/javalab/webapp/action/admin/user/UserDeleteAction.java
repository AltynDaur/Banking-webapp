package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.dao.h2Impl.H2UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int userID = Integer.parseInt(req.getParameter("userID"));
        UserDAO h2UserDAO = new H2UserDAO();
        h2UserDAO.deleteByID(userID);
        ActionResult result = new ActionResult("admin/users",true);
        return result;
    }
}
