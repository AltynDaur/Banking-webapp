package com.epam.javalab.webapp.controller.admin.updatedatabeans;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.service.ExchangeRateService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ManagedBean
@ViewScoped
public class UpdateExchangeRateBean {

    @Inject
    private ExchangeRateService exchangeRateService;

    private ExchangeRate selectedExchangeRate;

    public String update(ExchangeRate exchangeRate) {
        if (exchangeRate != null) {
            exchangeRate.setLocalDateTime(LocalDateTime.now());
            exchangeRateService.update(exchangeRate);
            selectedExchangeRate = null;
            return "exchangeRatesPages?faces-redirect=true";
        }
        return null;
    }

    public void select(ExchangeRate exchangeRate) {
        selectedExchangeRate = exchangeRate;
    }

    public boolean isSelected(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            return false;
        } else if (selectedExchangeRate == null) {
            return false;
        } else {
            return exchangeRate.equals(selectedExchangeRate);
        }
    }
}
