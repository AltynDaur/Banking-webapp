package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/exchangeRatesPage")
public class ExchangeRatesPageAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            req.setAttribute("action", "add");

        } else if (action.equals("update")) {
            req.setAttribute("action", "update");
            int exchangeRateID = Integer.parseInt(req.getParameter("currencyID"));
            ExchangeRate rate = null;
            try {
                rate = exchangeRateDAO.findByID(exchangeRateID);
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/exchangeRates");
            }
            req.setAttribute("exchangeRate", rate);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/exchangeRatesPage.jsp").forward(req, resp);
    }


}
