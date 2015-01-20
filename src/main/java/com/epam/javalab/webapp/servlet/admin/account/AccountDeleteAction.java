package com.epam.javalab.webapp.servlet.admin.account;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteAccount")
public class AccountDeleteAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountID = Integer.parseInt(req.getParameter("accountID"));
        try {

            accountDAO.delete(accountID);
        } catch (PersistenceException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("accounts");
        }
        req.setAttribute("message", "Account successfully deleted");
        resp.sendRedirect("accounts");
    }


}
