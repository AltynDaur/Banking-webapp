package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccTypeUpdateAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String accTypeName = req.getParameter("accTypeName");
        int percent = Integer.parseInt(req.getParameter("percent"));
        int period = Integer.parseInt(req.getParameter("period"));
        String currency = req.getParameter("currency");
        int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
        AccountType targetAccType = new AccountType();
        targetAccType.setId(accTypeID);
        targetAccType.setName(accTypeName);
        targetAccType.setPeriod(period);
        targetAccType.setPercent(percent);
        targetAccType.setCurrency(currency);
        H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();
        h2AccountTypeDAO.update(targetAccType);
        ActionResult result = new ActionResult("admin/accountTypes", true);
        return result;
    }
}
