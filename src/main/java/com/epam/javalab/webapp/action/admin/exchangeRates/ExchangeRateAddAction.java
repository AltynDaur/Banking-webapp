package com.epam.javalab.webapp.action.admin.exchangeRates;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addExchangeRate")
public class ExchangeRateAddAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currency = req.getParameter("currency");
        int exchangeValue = Integer.parseInt(req.getParameter("exchangeValue"));
        ExchangeRate rate = new ExchangeRate(currency,exchangeValue);
        try {
            exchangeRateDAO.add(rate);
            req.setAttribute("message", "Exchange rate successfully added");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/exchangeRates");
        }
        resp.sendRedirect("admin/exchangeRates");
    }

}
