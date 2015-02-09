package com.epam.javalab.webapp.converter;


import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.service.ExchangeRateService;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "exchangeRateConverterBean")
@FacesConverter(value = "exchangeRateConverter")
public class ExchangeRateConverter implements Converter {

    @Inject
    private ExchangeRateService exchangeRateService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            return exchangeRateService.find(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            ExchangeRate exchangeRate = (ExchangeRate) value;
            return String.valueOf(exchangeRate.getId());
        }
        return null;
    }
}
