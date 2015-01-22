package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.data.SessionBean;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;


import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
@Stateless
public class UserService implements Serializable {

    @Inject
    private SessionBean sessionBean;

    @Inject
    @JPA
    private UserDAO userDAO;

    public String login(String name, String password) {
        password = EncryptByMD5.encrypt(password, name);
        User user = userDAO.findByNameAndPass(name, password);
        if (user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                sessionBean.setUser(user);
                return "admin";
            } else {
                sessionBean.setUser(user);
                return "client";
            }
        } else

            return null;
    }
}
