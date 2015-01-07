package com.epam.javalab.webapp.action.admin.exchangeRates;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2ExchangeRateDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExchangeRateAddAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String currency = req.getParameter("currency");
        int exchangeValue = Integer.parseInt(req.getParameter("exchangeValue"));
        ExchangeRate rate = new ExchangeRate(currency,exchangeValue);
        H2ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
        h2ExchangeRateDAO.add(rate);
        ActionResult result = new ActionResult("admin/exchangeRates",true);
        return result;
    }
}
