package com.epam.javalab.webapp.controller;


import com.epam.javalab.webapp.data.SessionBean;
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
import javax.servlet.http.HttpSession;

@Model
public class UserController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    @Named
    @Produces
    private User user;

    @PostConstruct
    private void init() {
        user = new User();
    }

    public String register() {

        return null;
    }

    public String login() {

        try {

            user = userService.login(user.getName(), user.getPassword());
            if (user != null) {
                if (user.getRole().equals(Role.ADMIN)) {
                    sessionBean.setUser(user);
                    return "admin";
                } else {
                    sessionBean.setUser(user);
                    return "client";
                }
            }

        } catch (Exception e) {
            String errorMessage = e.getLocalizedMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Failure!");
            facesContext.addMessage(null, facesMessage);
        }
        return null;

    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "logout";
    }
}
