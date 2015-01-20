package com.epam.javalab.webapp.servlet.admin.account;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/transactionHistory")
public class TransactionHistoryAction extends HttpServlet {

    @Inject
    @JPA
    private TransactionHistoryDAO historyDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TransactionHistoryRecord> currentList = null;
        try {
            currentList = historyDAO.findAll();
        } catch (PersistenceException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("adminMainPage");
        }
        req.setAttribute("transRecordsList", currentList);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminMainPage.jsp").forward(req, resp);
    }

}