package com.epam.javalab.webapp.action.admin.user;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class UsersListAction implements Action {
    final static int  number =100;
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2UserDAO h2UserDAO = new H2UserDAO();

        List<User> resultList = h2UserDAO.getAll();
        sort(null);


        req.setAttribute("usersList",resultList);
        req.setAttribute("accTypeList", null);
        req.setAttribute("accList", null);
        ActionResult result = new ActionResult("admin/adminMainPage");
        return result;
    }


}
