package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
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

@WebServlet("/admin/accountTypesPage")
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
            req.setAttribute("action", "add");

        } else if (action.equals("update")) {
            req.setAttribute("action", "update");
            int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
            AccountType targetAccType = null;
            try {
                targetAccType = accountTypeDAO.findAccTypeByID(accTypeID);
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accountTypes");
            }
            req.setAttribute("accType", targetAccType);
            List<ExchangeRate> exchangeRates = null;
            try {
                exchangeRates = exchangeRateDAO.findAll();
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accountTypes");
            }
            req.setAttribute("exchangeRates", exchangeRates);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/accountsTypePage.jsp").forward(req, resp);
    }

}
