package com.epam.javalab.webapp.controller.admin;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.service.ExchangeRateService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean
public class AdminExchangeRateController {

    @Inject
    private ExchangeRateService exchangeRateService;

    private ExchangeRate addingExchangeRate;

    public ExchangeRate getAddingExchangeRate() {
        return addingExchangeRate;
    }

    public void setAddingExchangeRate(ExchangeRate addingExchangeRate) {
        this.addingExchangeRate = addingExchangeRate;
    }
}
