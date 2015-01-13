package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExchangeRatesPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*String action = req.getParameter("action");
        ActionResult result = new ActionResult();
        if(action.equals("add")){
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            String currency = req.getParameter("currency");
            ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
            ExchangeRate rate = h2ExchangeRateDAO.findByName(currency);

            req.setAttribute("currency",rate.getCurrency());
            req.setAttribute("exchangeValue",rate.getValue());
        }
        result.setPath("admin/exchangeRatesPage");
        return result;*/
        return null;

    }
}
