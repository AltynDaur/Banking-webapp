package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccTypeDeleteAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
        H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();
        h2AccountTypeDAO.delete(accTypeID);
        ActionResult result = new ActionResult("admin/accountTypes",true);
        return result;
    }
}
