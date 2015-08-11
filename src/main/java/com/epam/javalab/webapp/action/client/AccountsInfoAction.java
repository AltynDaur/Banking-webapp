package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountsInfoAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User currentUser = (User) req.getSession().getAttribute("user");
        int currentID = currentUser.getId();
        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        List<Account> currentAccList = h2AccountDAO.findAllByUserID(currentID);
        req.setAttribute("accList",currentAccList);
        ActionResult result = new ActionResult("client/clientMainPage");
        return result;
    }
}
