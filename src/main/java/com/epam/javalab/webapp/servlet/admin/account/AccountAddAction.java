package com.epam.javalab.webapp.servlet.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/admin/addAccount")
public class AccountAddAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;
    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;
    @Inject
    @JPA
    private UserDAO userDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        int accTypeID = Integer.parseInt(req.getParameter("accTypeID"));
        long amount = Long.parseLong(req.getParameter("amount"));

        try {
            AccountType accType = accountTypeDAO.findAccTypeByID(accTypeID);
            User user = userDAO.findByName(userName);
            LocalDateTime currentTime = LocalDateTime.now();
            Account targetAccount = new Account(user, accType, amount,currentTime);
            accountDAO.add(targetAccount);
        } catch (PersistenceException e) {
            req.setAttribute("message", "Database problems!");
            resp.sendRedirect("accounts");
        }

        req.setAttribute("message", "Account successfully added");
        resp.sendRedirect("accounts");
    }
}
