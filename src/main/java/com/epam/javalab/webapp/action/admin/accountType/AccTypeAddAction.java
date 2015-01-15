package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accTypeName = req.getParameter("accTypeName");
        int percent = Integer.parseInt(req.getParameter("percent"));
        int period = Integer.parseInt(req.getParameter("period"));
        ExchangeRate exchangeRate = (ExchangeRate) req.getAttribute("exchangeRate");
        AccountType targetAccType = new AccountType(accTypeName, percent, period, exchangeRate);
        try {
            accountTypeDAO.add(targetAccType);
            req.setAttribute("message", "Account type successfully added");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/accountTypes");
        }
        resp.sendRedirect("admin/accountTypes");
    }

}
