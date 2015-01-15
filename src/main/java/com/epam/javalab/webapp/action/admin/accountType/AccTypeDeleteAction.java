package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.account.AccountType;
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

@WebServlet("/admin/deleteAccType")
public class AccTypeDeleteAction extends HttpServlet {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountType accountType = (AccountType) req.getAttribute("accType");
        try {
            accountTypeDAO.delete(accountType);
            req.setAttribute("message", "AccountType successfully deleted");
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/accountTypes");
        }
        resp.sendRedirect("admin/accountTypes");
    }


}
