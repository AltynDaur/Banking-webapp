package com.epam.javalab.webapp.controller.admin;


import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Model
public class AdminUsersController {


    @Inject
    private FacesContext facesContext;

    @Inject
    private UserService userService;

    @Named
    @Produces
    private User addedUser;


    private List<Role> roles;

    private int id;

    @PostConstruct
    private void init() {
        addedUser = new User();
        roles = new ArrayList();
        for (int i = 0; i < Role.values().length; i++) {
            roles.add(Role.values()[i]);
        }
    }

    @Named
    @Produces
    public List<Role> getRoles() {
        return roles;
    }

    public String add() {
        addedUser.setPassword(EncryptByMD5.encrypt(addedUser.getPassword(), addedUser.getName()));
        userService.register(addedUser);
        return "usersPage?faces-redirect=true";
    }



    public void delete(int id) {
        userService.delete(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
