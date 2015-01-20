package com.epam.javalab.webapp.servlet.admin;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
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

@WebServlet("/admin/accountsTypePage")
public class AccountsTypePageAction extends HttpServlet {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            List<ExchangeRate> exchangeRates = null;
            try {
                exchangeRates = exchangeRateDAO.findAll();
            } catch (NoResultException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accountTypes");
            }
            req.setAttribute("exchangeRates", exchangeRates);
            req.setAttribute("action", "add");

        } else if (action.equals("update")) {
            req.setAttribute("action", "update");
            int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
            AccountType targetAccType = null;
            try {
                targetAccType = accountTypeDAO.findAccTypeByID(accTypeID);
                req.setAttribute("accType", targetAccType);
                List<ExchangeRate> exchangeRates = exchangeRateDAO.findAll();
                req.setAttribute("exchangeRates", exchangeRates);
            } catch (NoResultException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accountTypes");
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/accountsTypePage.jsp").forward(req, resp);
    }

}
