package com.epam.javalab.webapp.action.client;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountDAO;
import com.epam.javalab.webapp.dao.H2TransactionHistoryDAO;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionHistoryAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User currentUser = (User) req.getSession().getAttribute("user");
        int currentID = currentUser.getId();
        List<TransactionHistoryRecord> currentList = new ArrayList<TransactionHistoryRecord>();
        H2AccountDAO h2AccountDAO = new H2AccountDAO();
        H2TransactionHistoryDAO h2TransactionHistoryDAO = new H2TransactionHistoryDAO();
        List<Account>currentAccounts = h2AccountDAO.findAllByUserID(currentID);
        for (Account currentAccount : currentAccounts) {
            currentList.addAll(h2TransactionHistoryDAO.find(currentAccount.getId()));
        }
        req.setAttribute("transHistoryRecords", currentList);
        ActionResult actionResult = new ActionResult("client/clientMainPage");
        return actionResult;
    }
}
