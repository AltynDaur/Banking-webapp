package com.epam.javalab.webapp.controller.admin;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.service.ExchangeRateService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Model
public class AdminExchangeRateController {

    @Inject
    private ExchangeRateService exchangeRateService;

    private ExchangeRate addingExchangeRate;

    @PostConstruct
    private void init() {
        addingExchangeRate = new ExchangeRate();
    }

    public String add() {
        addingExchangeRate.setLocalDateTime(LocalDateTime.now());
        exchangeRateService.add(addingExchangeRate);
        return "exchangeRatesPage?faces-redirect=true";
    }

    public void delete(int id) {
        exchangeRateService.delete(id);
    }

    public ExchangeRate getAddingExchangeRate() {
        return addingExchangeRate;
    }

    public void setAddingExchangeRate(ExchangeRate addingExchangeRate) {
        this.addingExchangeRate = addingExchangeRate;
    }
}
