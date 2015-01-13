package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.h2Impl.H2UserDAO;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;



public class UsersListAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserDAO h2UserDAO = new H2UserDAO();
        List<User> resultList = h2UserDAO.getAll();
        req.setAttribute("usersList",resultList);
        req.setAttribute("accTypeList", null);
        req.setAttribute("accList", null);
        ActionResult result = new ActionResult("admin/adminMainPage");
        return result;
    }


}
