package com.epam.javalab.webapp.servlet.client;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/client/transactionHistory")
public class UserTransactionHistoryAction extends HttpServlet {

    @Inject
    @JPA
    private TransactionHistoryDAO transactionHistoryDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        int currentID = currentUser.getId();
        try {
            List<TransactionHistoryRecord> historyRecords = transactionHistoryDAO.findByUserID(currentID);
            req.setAttribute("transHistoryRecords", historyRecords);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/client/clientMainPage.jsp").forward(req, resp);
    }

}
