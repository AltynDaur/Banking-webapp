package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.AccountDAO;
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
import java.util.List;

@WebServlet("/admin/accountsPage")
public class AccountsPageAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("add")){
            List<AccountType> accountTypes = null;
            try {
                accountTypes = accountTypeDAO.findAll();
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accounts");
            }
            req.setAttribute("accountTypes", accountTypes);
            req.setAttribute("action","add");
        } else if(action.equals("update")){
            req.setAttribute("action","update");
            int accID = Integer.parseInt(req.getParameter("accID"));
            Account targetAccount = null;
            try {
                targetAccount = accountDAO.findByID(accID);
            } catch (DAOException e) {
                req.setAttribute("message", "Database problems");
                resp.sendRedirect("admin/accounts");
            }
            req.setAttribute("account", targetAccount);

        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/accountsPage.jsp").forward(req, resp);
    }


}
