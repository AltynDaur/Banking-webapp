package com.epam.javalab.webapp.controller;


import com.epam.javalab.webapp.data.SessionBean;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;

@Model
public class LoginController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    @Named
    @Produces
    private User loginUser;

    private String repeatPassword;

    @PostConstruct
    private void init() {
        loginUser = new User();
    }


    public String login() {

        try {

            loginUser = userService.login(loginUser.getName(), loginUser.getPassword());
            if (loginUser != null) {
                if (loginUser.getRole().equals(Role.ADMIN)) {
                    sessionBean.setUser(loginUser);
                    return "admin";
                } else {
                    sessionBean.setUser(loginUser);
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
