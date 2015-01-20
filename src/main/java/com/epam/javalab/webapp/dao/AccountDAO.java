package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllByUserID(int id);

    public List<Account> findAll();

    public Account findByID(int id);

    public void update(Account account);

    public void delete(int accountID);

    public void add(Account account);

    boolean transaction(Account currentAccount, Account targetAccount, long amount);
}
