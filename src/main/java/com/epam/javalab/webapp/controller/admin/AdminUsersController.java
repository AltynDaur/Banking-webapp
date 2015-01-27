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

    private List<User> users;

    private String currentPassword;

    @PostConstruct
    private void init() {
        addedUser = new User();
        retrieveAllUsers();
        roles = new ArrayList();
        for (int i = 0; i < Role.values().length; i++) {
            roles.add(Role.values()[i]);
        }
    }

    @Named
    @Produces
    public List<User> getUsers() {
        return users;
    }

    @Named
    @Produces
    public List<Role> getRoles() {
        return roles;
    }

    public void add() {
        addedUser.setPassword(EncryptByMD5.encrypt(addedUser.getPassword(), addedUser.getName()));
        userService.register(addedUser);
    }

    public void update() {

    }

    public void delete(int id) {
        userService.delete(id);
    }

    public void onUsersListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllUsers();
    }

    private void retrieveAllUsers() {
        users = userService.findAll();
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
