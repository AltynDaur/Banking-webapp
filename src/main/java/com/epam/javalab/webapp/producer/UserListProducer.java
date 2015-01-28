package com.epam.javalab.webapp.producer;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.user.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class UserListProducer {

    @Inject
    @JPA
    private UserDAO userDAO;

    private List<User> users;

    @Produces
    @Named
    public List<User> getUsers() {
        if (users == null) {
            retrieveAllUsers();
        }
        return users;
    }

    public void onUsersListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllUsers();
    }

    private void retrieveAllUsers() {
        users = userDAO.getAll();
    }
}
