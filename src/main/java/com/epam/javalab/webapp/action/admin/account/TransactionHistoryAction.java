package com.epam.javalab.webapp.action.admin.account;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2TransactionHistoryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TransactionHistoryAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2TransactionHistoryDAO h2TransactionHistoryDAO = new H2TransactionHistoryDAO();
        List<TransactionHistoryRecord> currentList = h2TransactionHistoryDAO.findAll();
        req.setAttribute("transRecordsList", currentList);
        ActionResult result = new ActionResult("admin/adminMainPage");
        return result;
    }
}
