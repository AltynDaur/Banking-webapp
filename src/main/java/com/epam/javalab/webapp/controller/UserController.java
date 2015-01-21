package com.epam.javalab.webapp.controller;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class UserController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Named
    @Produces
    private User user;

    @PostConstruct
    private void initUser() {
        user = new User();
    }

    public String register() {

        return null;
    }

    public String login() {
        String path = null;
        try {

            path = userService.login(user.getName(), user.getPassword());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged In", "Success!");
            facesContext.addMessage(null, facesMessage);


        } catch (Exception e) {
            String errorMessage = e.getLocalizedMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Failure!");
            facesContext.addMessage(null, facesMessage);
        }
        return path;

    }
}
