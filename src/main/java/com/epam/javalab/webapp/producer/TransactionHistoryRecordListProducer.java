package com.epam.javalab.webapp.producer;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class TransactionHistoryRecordListProducer {

    @Inject
    @JPA
    private TransactionHistoryDAO transactionHistoryDAO;

    private List<TransactionHistoryRecord> transactionHistoryRecords;


    private void onTransactionRecordListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) TransactionHistoryRecord transactionHistoryRecord) {
        retrieveAllTransactionRecords();
    }

    private void retrieveAllTransactionRecords() {
        transactionHistoryRecords = transactionHistoryDAO.findAll();
    }

    @Named
    @Produces
    public List<TransactionHistoryRecord> getTransactionHistoryRecords() {
        if (transactionHistoryRecords == null) {
            retrieveAllTransactionRecords();
        }
        return transactionHistoryRecords;
    }

    public void setTransactionHistoryRecords(List<TransactionHistoryRecord> transactionHistoryRecords) {
        this.transactionHistoryRecords = transactionHistoryRecords;
    }
}
