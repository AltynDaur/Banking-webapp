package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface AccountTypeDAO {
    public List<AccountType> findAll();

    public void add(AccountType accountType);

    public void update(AccountType targetAccType);

    public void delete(int accountTypeID);

    public AccountType findAccTypeByID(int accTypeID);

    public AccountType findByName(String name);
}
