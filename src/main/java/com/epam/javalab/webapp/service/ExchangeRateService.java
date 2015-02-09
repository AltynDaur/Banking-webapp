package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class ExchangeRateService {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    @Inject
    private Event<ExchangeRate> exchangeRateEvent;

    public ExchangeRate findByName(String name) {
        return exchangeRateDAO.findByName(name);
    }

    public ExchangeRate find(int id) {
        return exchangeRateDAO.findByID(id);
    }

    public void add(ExchangeRate exchangeRate) {
        exchangeRateDAO.add(exchangeRate);
        exchangeRateEvent.fire(exchangeRate);

    }

    public void update(ExchangeRate exchangeRate) {
        exchangeRateDAO.update(exchangeRate);
        exchangeRateEvent.fire(exchangeRate);
    }

    public void delete(int id) {
        exchangeRateDAO.delete(id);
        exchangeRateEvent.fire(new ExchangeRate());
    }
}
