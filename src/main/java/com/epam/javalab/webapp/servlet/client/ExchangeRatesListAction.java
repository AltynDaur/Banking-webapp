package com.epam.javalab.webapp.action.client;

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
import java.util.List;

@WebServlet("/client/exchangeRates")
public class ExchangeRatesListAction extends HttpServlet {


    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<ExchangeRate> currentList = exchangeRateDAO.findAll();
            req.setAttribute("exchangeRates", currentList);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/client/clientMainPage.jsp").forward(req, resp);

    }


}
