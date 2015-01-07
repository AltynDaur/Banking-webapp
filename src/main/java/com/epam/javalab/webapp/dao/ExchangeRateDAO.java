package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.ExchangeRate;

import java.util.List;

public interface ExchangeRateDAO {
    public List<ExchangeRate> findAll();
    public void add(ExchangeRate rate);
    public void deleteByID(int id);
    public void update(ExchangeRate rate);
}
