package com.epam.javalab.webapp.converter;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.service.AccountService;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "userConverterBean")
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {
    @Inject
    private UserService userService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && value.trim().length() > 0) {
            return userService.find(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            User user = (User) value;
            return String.valueOf(user.getId());
        }
        return null;
    }
}
