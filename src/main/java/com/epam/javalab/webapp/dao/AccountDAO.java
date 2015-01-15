package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllByUserID(int id);

    public List<Account> findAll() throws DAOException;

    public Account findByID(int id) throws DAOException;

    public void update(Account account) throws DAOException;

    public void delete(Account account) throws DAOException;

    public void add(Account account) throws DAOException;

    boolean transaction(Account currentAccount, Account targetAccount, long amount) throws DAOException;
}
