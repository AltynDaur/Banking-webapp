package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountUpdateAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int accID = Integer.parseInt(req.getParameter("accID"));
        long amount = Long.parseLong(req.getParameter("amount"));

        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        h2AccountDAO.update(accID, amount);
        ActionResult result = new ActionResult("admin/accounts",true);
        return result;
    }
}
