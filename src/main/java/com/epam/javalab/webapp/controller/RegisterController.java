package com.epam.javalab.webapp.controller;

import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class RegisterController {
    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Named
    @Produces
    private User newUser;

    @RequestScoped
    private String repeatPassword;

    @PostConstruct
    private void init() {
        newUser = new User();
    }

    public String register() {
        if (EncryptByMD5.encrypt(newUser.getPassword(), newUser.getName()).equals(EncryptByMD5.encrypt(repeatPassword, newUser.getName()))) {
            newUser.setPassword(EncryptByMD5.encrypt(newUser.getPassword(), newUser.getName()));
            newUser.setRole(Role.CLIENT);
            userService.register(newUser);
            return "loginPage";
        }
        return null;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

}
