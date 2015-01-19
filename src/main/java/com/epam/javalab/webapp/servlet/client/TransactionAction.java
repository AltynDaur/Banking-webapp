package com.epam.javalab.webapp.servlet.client;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/transaction")
public class TransactionAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Inject
    @JPA
    private TransactionHistoryDAO transactionHistoryDAO;

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentAccID = Integer.parseInt(req.getParameter("currentAccID"));
        int targetAccID = Integer.parseInt(req.getParameter("targetAccID"));
        long amount = Long.parseLong(req.getParameter("amount"));
        int exchangeRateID = Integer.parseInt(req.getParameter("exchangeRateID"));
        try {
            ExchangeRate transactionExchangeRate = exchangeRateDAO.findByID(exchangeRateID);
            long amountForTransaction = (long) (amount * transactionExchangeRate.getValue());
            Account currentAccount = accountDAO.findByID(currentAccID);
            Account targetAccount = accountDAO.findByID(targetAccID);
            boolean transactionStatusOK = accountDAO.transaction(currentAccount, targetAccount, amountForTransaction);
            if (transactionStatusOK) {
                TransactionHistoryRecord record = new TransactionHistoryRecord(currentAccount, targetAccount, amount, transactionExchangeRate);
                transactionHistoryDAO.add(record);
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("accountsInfo");

    }

}
