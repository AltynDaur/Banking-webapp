package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface AccountTypeDAO {
    public List<AccountType> findAll() throws DAOException;

    public void add(AccountType accountType) throws DAOException;

    public void update(AccountType targetAccType) throws DAOException;

    public void delete(int accountTypeID) throws DAOException;

    public AccountType findAccTypeByID(int accTypeID) throws DAOException;
}
