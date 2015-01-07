package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllByUserID(int id);
    public void update(int accID, long amount);
    public void deleteByID(int account);
    public void add(Account account);


    boolean transaction(int currentAccID, int targetAccID, long amount);
}
