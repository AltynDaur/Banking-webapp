package com.epam.javalab.webapp.action.admin.exchangeRates;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2ExchangeRateDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExchangeRateDeleteAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int exchangeRateID = Integer.parseInt(req.getParameter("exchangeRateID"));
        H2ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
        h2ExchangeRateDAO.deleteByID(exchangeRateID);
        ActionResult result = new ActionResult("admin/exchangeRates", true);
        return result;
    }
}
