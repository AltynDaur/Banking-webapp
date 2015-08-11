package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountsTypePageAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        ActionResult result = new ActionResult();
        if(action.equals("add")){
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
            H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();
            AccountType targetAccType = h2AccountTypeDAO.findAccTypeByID(accTypeID);
            req.setAttribute("accTypeID",targetAccType.getId());
            req.setAttribute("accTypeName", targetAccType.getName());
            req.setAttribute("percent", targetAccType.getPercent());
            req.setAttribute("period", targetAccType.getPeriod());
            req.setAttribute("currency", targetAccType.getCurrency());
        }
        result.setPath("admin/accountsTypePage");
        return result;
    }
}
