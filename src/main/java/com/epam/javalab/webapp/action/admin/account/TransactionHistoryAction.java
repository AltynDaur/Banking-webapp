package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;

import javax.inject.Inject;
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
        List<TransactionHistoryRecord> currentList = historyDAO.findAll();
        req.setAttribute("transRecordsList", currentList);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminMainPage.jsp").forward(req, resp);
    }

}
