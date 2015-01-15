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

@WebServlet("/admin/deleteExchangeRate")
public class ExchangeRateDeleteAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExchangeRate exchangeRate = (ExchangeRate) req.getAttribute("exchangeRate");
        try {
            exchangeRateDAO.delete(exchangeRate);
            req.setAttribute("message", "Exchange rate successfully deleted");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/exchangeRates");
        }
        resp.sendRedirect("admin/exchangeRates");
    }

}
