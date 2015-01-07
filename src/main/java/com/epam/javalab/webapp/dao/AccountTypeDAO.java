package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.AccountType;

import java.util.List;

public interface AccountTypeDAO {
    public List<AccountType> findAll();
    public void add(AccountType accountType);
    public void update(AccountType targetAccType);
    public void delete(int accTypeID);

    public AccountType findAccTypeByID(int accTypeID);
}
