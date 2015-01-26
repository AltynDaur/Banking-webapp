package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Stateless
public class UserService implements Serializable {

    @Inject
    @JPA
    private UserDAO userDAO;

    @Inject
    private Event<User> userEvent;

    public User login(String name, String password) {
        password = EncryptByMD5.encrypt(password, name);
        User user = userDAO.findByNameAndPass(name, password);
        return user;
    }

    public void register(User user) {
        userDAO.add(user);
        userEvent.fire(user);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }

    public List<User> findAll() {
        return userDAO.getAll();

    }
}
