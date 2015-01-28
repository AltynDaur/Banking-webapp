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

    private String oldPassword;

    private String currentPassword;

    private String repeatedPassword;

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

    public void add() {
        addedUser.setPassword(EncryptByMD5.encrypt(addedUser.getPassword(), addedUser.getName()));
        userService.register(addedUser);
    }

    public void update(User user) {
        userService.update(user);
    }

    public void delete(int id) {
        userService.delete(id);
    }

    public void updatePassword(User user) {
        if (user.getPassword().equals(EncryptByMD5.encrypt(oldPassword, user.getName()))) {
            if (currentPassword.equals(repeatedPassword)) {
                user.setPassword(EncryptByMD5.encrypt(currentPassword, user.getName()));
                userService.update(user);
            } else {
                facesContext.addMessage("repeatPass", new FacesMessage("Repeated password not equals new password"));
            }
        } else {
            facesContext.addMessage("oldPass", new FacesMessage("You entered wrong password!"));
        }
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
