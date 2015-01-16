package com.epam.javalab.webapp.servlet.admin.account;

import com.epam.javalab.webapp.account.Account;
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
import java.util.List;

@WebServlet("/admin/accounts")
public class AccountsListAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accountList = null;
        try {
            accountList = accountDAO.findAll();
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/adminMainPage");
        }
        req.setAttribute("accList",accountList);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminMainPage.jsp").forward(req, resp);
    }

}
