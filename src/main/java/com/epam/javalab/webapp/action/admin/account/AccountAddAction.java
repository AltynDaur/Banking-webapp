package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountAddAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String accTypeName = req.getParameter("accTypeName");
        long amount = Long.parseLong(req.getParameter("amount"));
        Account targetAccount = new Account(userName,accTypeName,amount);
        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        h2AccountDAO.add(targetAccount);
        ActionResult result = new ActionResult("admin/accounts",true);
        return result;
    }
}
