package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountsListAction implements Action {

    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        List<Account> accountList = h2AccountDAO.findAll();
        req.setAttribute("accList",accountList);
        req.setAttribute("usersList", null);
        req.setAttribute("accTypeList",null);
        ActionResult result = new ActionResult("admin/adminMainPage");
        return result;
    }
}
