package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
@Stateful
public class UserService implements Serializable {

    private User user;

    @Inject
    @JPA
    private UserDAO userDAO;

    public String login(String name, String password) {
        password = EncryptByMD5.encrypt(password, name);
        user = userDAO.findByNameAndPass(name, password);
        if (user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                return "admin";
            } else return "client";
        } else

            return null;
    }
}
