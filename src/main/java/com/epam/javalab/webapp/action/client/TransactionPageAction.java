package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentAccID = Integer.parseInt(req.getParameter("accID"));
        req.setAttribute("currentAccID",currentAccID);
        ActionResult result = new ActionResult("client/transactionPage");
        return result;
    }
}
