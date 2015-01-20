package com.epam.javalab.webapp.servlet.admin.exchangeRates;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/exchangeRates")
public class ExchangeRatesAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ExchangeRate> currentList = null;
        try {
            currentList = exchangeRateDAO.findAll();
        } catch (NoResultException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/adminMainPage");
        }
        req.setAttribute("exchangeRates", currentList);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminMainPage.jsp").forward(req, resp);
    }

}
