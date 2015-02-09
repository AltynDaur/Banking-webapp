package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@RequestScoped
@Stateless
public class TransactionHistoryRecordService {

    @Inject
    @JPA
    private TransactionHistoryDAO transactionHistoryDAO;

    @Inject
    private Event<TransactionHistoryRecord> transactionHistoryRecordEvent;

    public void add(TransactionHistoryRecord transactionHistoryRecord) {
        transactionHistoryDAO.add(transactionHistoryRecord);
        transactionHistoryRecordEvent.fire(transactionHistoryRecord);
    }
}
