package com.epam.javalab.webapp.servlet.client;


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

@WebServlet("/client/transactionPage")
public class TransactionPageAction extends HttpServlet {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentAccID = Integer.parseInt(req.getParameter("accID"));
        try {
            List<ExchangeRate> exchangeRates = exchangeRateDAO.findAll();
            req.setAttribute("exchangeRates", exchangeRates);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        req.setAttribute("currentAccID", currentAccID);
        req.getRequestDispatcher("/WEB-INF/jsp/client/transactionPage.jsp").forward(req, resp);
    }

}
