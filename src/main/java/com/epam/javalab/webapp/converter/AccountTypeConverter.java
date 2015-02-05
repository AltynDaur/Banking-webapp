package com.epam.javalab.webapp.converter;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.service.AccountTypeService;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "accountTypeConverterBean")
@FacesConverter(value = "accountTypeConverter")
public class AccountTypeConverter implements Converter {
    @Inject
    private AccountTypeService accountTypeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            AccountType accountType = accountTypeService.findByName(value);
            return accountType;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return null;
    }
}
