package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ExchangeRateService {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    public ExchangeRate findByName(String name) {
        return exchangeRateDAO.findByName(name);
    }
}
