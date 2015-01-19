package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface ExchangeRateDAO {
    public List<ExchangeRate> findAll() throws DAOException;

    public void add(ExchangeRate rate) throws DAOException;

    public void delete(int rateID) throws DAOException;

    public void update(ExchangeRate rate) throws DAOException;

    public ExchangeRate findByID(int currencyID) throws DAOException;
}
