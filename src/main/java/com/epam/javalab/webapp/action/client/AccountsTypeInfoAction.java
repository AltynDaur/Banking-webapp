package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountsTypeInfoAction implements Action {

    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();
        List<AccountType> accountTypeList = h2AccountTypeDAO.findAll();
        req.setAttribute("accTypeList", accountTypeList);
        ActionResult result = new ActionResult("client/clientMainPage");
        return result;
    }
}
