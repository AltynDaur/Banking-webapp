package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllByUserID(int id);

    public void update(Account account);

    public void delete(Account account);
    public void add(Account account);

    boolean transaction(Account currentAccount, Account targetAccount, long amount);
}
