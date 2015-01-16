package com.epam.javalab.webapp.servlet.admin.exchangeRates;

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

@WebServlet("/admin/updateExchangeRate")
public class ExchangeRateUpdateAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int exchangeRateID = (int) req.getAttribute("exchangeRateID");
        int exchangeValue = Integer.parseInt(req.getParameter("exchangeValue"));

        try {
            ExchangeRate exchangeRate = exchangeRateDAO.findByID(exchangeRateID);
            exchangeRate.setValue(exchangeValue);
            exchangeRateDAO.update(exchangeRate);
            req.setAttribute("message", "Database problems");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("exchangeRates");
        }
        resp.sendRedirect("exchangeRates");
    }


}
