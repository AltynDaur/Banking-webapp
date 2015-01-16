package com.epam.javalab.webapp.servlet.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addAccount")
public class AccountAddAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;
    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
        long amount = Long.parseLong(req.getParameter("amount"));
        AccountType accType = null;
        try {
            accType = accountTypeDAO.findAccTypeByID(accTypeID);
        } catch (DAOException e) {
            req.setAttribute("message", "Can't find this Account type!");
            resp.sendRedirect("accounts");
        }
        Account targetAccount = new Account(user, accType, amount);
        try {
            accountDAO.add(targetAccount);

        } catch (DAOException e) {
            req.setAttribute("message", "Account adding error!");
            resp.sendRedirect("accounts");
        }
        req.setAttribute("message", "Account successfully added");
        resp.sendRedirect("accounts");
    }
}
