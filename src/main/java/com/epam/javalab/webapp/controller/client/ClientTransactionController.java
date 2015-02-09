package com.epam.javalab.webapp.controller.client;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import com.epam.javalab.webapp.service.AccountService;
import com.epam.javalab.webapp.service.TransactionHistoryRecordService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class ClientTransactionController {

    @Inject
    private TransactionHistoryRecordService transactionHistoryService;

    @Inject
    private AccountService accountService;

    @Named
    @Produces
    private TransactionHistoryRecord transactionHistoryRecord;

    @PostConstruct
    private void init() {
        transactionHistoryRecord = new TransactionHistoryRecord();
    }

    public String transaction() {
        transactionHistoryRecord.setStartAcc(accountService.findByID(transactionHistoryRecord.getStartAcc().getId()));
        transactionHistoryRecord.setFinalAcc(accountService.findByID(transactionHistoryRecord.getFinalAcc().getId()));
        boolean transactionStatusOK = accountService.transactionBtwnAccounts(transactionHistoryRecord.getStartAcc(), transactionHistoryRecord.getFinalAcc(), transactionHistoryRecord.getAmount());
        if (transactionStatusOK) {
            transactionHistoryService.add(transactionHistoryRecord);
        }
        return "clientAccountsPage?faces-redirect=true";
    }

    public TransactionHistoryRecord getTransactionHistoryRecord() {
        return transactionHistoryRecord;
    }

    public void setTransactionHistoryRecord(TransactionHistoryRecord transactionHistoryRecord) {
        this.transactionHistoryRecord = transactionHistoryRecord;
    }
}
