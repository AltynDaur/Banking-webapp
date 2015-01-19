package com.epam.javalab.webapp.servlet.admin.accountType;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
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

@WebServlet("/admin/addAccType")
public class AccTypeAddAction extends HttpServlet {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;
    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accTypeName = req.getParameter("accTypeName");
        int percent = Integer.parseInt(req.getParameter("percent"));
        int period = Integer.parseInt(req.getParameter("period"));
        int exchangeRateID = Integer.parseInt(req.getParameter("exchangeRateID"));
        ExchangeRate exchangeRate = null;
        try {
            exchangeRate = exchangeRateDAO.findByID(exchangeRateID);
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            req.getRequestDispatcher("").forward(req, resp);
        }
        AccountType targetAccType = new AccountType(accTypeName, percent, period, exchangeRate);
        try {
            accountTypeDAO.add(targetAccType);
            req.setAttribute("message", "Account type successfully added");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("accountTypes");
        }
        resp.sendRedirect("accountTypes");
    }

}
