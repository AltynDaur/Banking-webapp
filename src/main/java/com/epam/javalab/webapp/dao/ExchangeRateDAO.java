package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.exception.DAOException;

import java.util.List;

public interface ExchangeRateDAO {
    public List<ExchangeRate> findAll();

    public void add(ExchangeRate rate);

    public void delete(int rateID);

    public void update(ExchangeRate rate);

    public ExchangeRate findByID(int currencyID);

    public ExchangeRate findByName(String name);
}
