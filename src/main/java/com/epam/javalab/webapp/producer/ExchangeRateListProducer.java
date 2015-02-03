package com.epam.javalab.webapp.producer;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class ExchangeRateListProducer {

    @Inject
    @JPA
    private ExchangeRateDAO exchangeRateDAO;

    private List<ExchangeRate> exchangeRates;

    @Named
    @Produces
    public List<ExchangeRate> getExchangeRates() {
        if (exchangeRates == null) {
            retrieveAllExchangeRates();
        }
        return exchangeRates;
    }

    public void onUsersListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final ExchangeRate exchangeRate) {
        retrieveAllExchangeRates();
    }

    private void retrieveAllExchangeRates() {
        exchangeRates = exchangeRateDAO.findAll();
    }
}
