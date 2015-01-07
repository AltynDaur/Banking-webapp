package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;

import java.util.List;

public interface TransactionHistoryDAO {
    public void add(TransactionHistoryRecord record);
    public List<TransactionHistoryRecord> find(int accID);
}
