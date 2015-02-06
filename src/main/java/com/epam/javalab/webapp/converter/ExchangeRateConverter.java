package com.epam.javalab.webapp.converter;


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
            return exchangeRateService.findByName(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
