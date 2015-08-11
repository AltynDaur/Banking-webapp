package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2ExchangeRateDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExchangeRatesPageAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        ActionResult result = new ActionResult();
        if(action.equals("add")){
            req.setAttribute("action","add");

        } else if(action.equals("update")){
            req.setAttribute("action","update");
            String currency = req.getParameter("currency");
            H2ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
            ExchangeRate rate = h2ExchangeRateDAO.findByName(currency);

            req.setAttribute("currency",rate.getCurrency());
            req.setAttribute("exchangeValue",rate.getValue());
        }
        result.setPath("admin/exchangeRatesPage");
        return result;

    }
}
