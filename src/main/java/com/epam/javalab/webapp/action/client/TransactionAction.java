package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;
import com.epam.javalab.webapp.dao.H2ExchangeRateDAO;
import com.epam.javalab.webapp.dao.H2TransactionHistoryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int currentAccID = Integer.parseInt(req.getParameter("currentAccID"));
        int targetAccID = Integer.parseInt(req.getParameter("targetAccID"));
        long amount = Long.parseLong(req.getParameter("amount"));
        String transactionCurrency = req.getParameter("currency").toUpperCase();
        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        H2ExchangeRateDAO h2ExchangeRateDAO = new H2ExchangeRateDAO();
        H2TransactionHistoryDAO h2TransactionHistoryDAO = new H2TransactionHistoryDAO();
        ExchangeRate rate = h2ExchangeRateDAO.findByName(transactionCurrency);
        long amountForTransaction = amount*rate.getValue();
        boolean transactionStatusOK = h2AccountDAO.transaction(currentAccID,targetAccID,amountForTransaction);
        if(transactionStatusOK){

            TransactionHistoryRecord record = new TransactionHistoryRecord(currentAccID,targetAccID,amount,transactionCurrency);
            h2TransactionHistoryDAO.add(record);
        }

        ActionResult result = new ActionResult("client/accountsInfo",true);
        return result;
    }
}
