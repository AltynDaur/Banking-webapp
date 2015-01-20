package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface TransactionHistoryDAO {

    public void add(TransactionHistoryRecord record);

    public List<TransactionHistoryRecord> findByUserID(int id);

    public List<TransactionHistoryRecord> findAll();

}
