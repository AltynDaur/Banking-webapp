package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountsPageAction  implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*String action = req.getParameter("action");
        ActionResult result = new ActionResult();
        if(action.equals("add")){
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            int accID = Integer.parseInt(req.getParameter("accID"));
            AccountDAO h2AccountDAO = new H2AccountDAO();
            Account targetAccount = h2AccountDAO.findByAccID(accID);
            req.setAttribute("accID",targetAccount.getId());
            req.setAttribute("amount",targetAccount.getAmount());
            req.setAttribute("owner", targetAccount.getOwner());
            req.setAttribute("accType", targetAccount.getAcctype());
        }
        result.setPath("admin/accountsPage");
        return result;*/
        return null;
    }
}
