package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/updateAccount")
public class AccountUpdateAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getAttribute("account");
        long amount = Long.parseLong(req.getParameter("amount"));
        account.setAmount(amount);
        try {
            accountDAO.update(account);
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/accounts");
        }
        req.setAttribute("message", "Account successfully updated");
        resp.sendRedirect("admin/accounts");
    }

}
