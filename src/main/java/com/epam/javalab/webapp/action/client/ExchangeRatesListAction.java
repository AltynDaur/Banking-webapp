package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2ExchangeRateDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ExchangeRatesListAction implements Action {

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
        List<ExchangeRate> currentList = h2ExchangeRateDAO.findAll();
        req.setAttribute("exchangeRates", currentList);
        ActionResult result = new ActionResult("client/clientMainPage");
        return result;
    }
}
