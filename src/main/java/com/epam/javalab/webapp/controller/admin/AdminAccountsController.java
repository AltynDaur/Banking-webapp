package com.epam.javalab.webapp.controller.admin;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.producer.UserListProducer;
import com.epam.javalab.webapp.service.AccountService;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Model
public class AdminAccountsController {

    @Inject
    private AccountService accountService;

    @Inject
    private UserService userService;

    @Inject
    private UserListProducer userListProducer;

    @Named
    @Produces
    private Account addedAccount;

    private List<User> clients;

    @PostConstruct
    private void init() {
        addedAccount = new Account();
        clients = new ArrayList<User>();
        retrieveAllClients();
    }

    public void add() {
        accountService.add(addedAccount);
    }

    public void update(Account account) {
        accountService.update(account);
    }

    public void delete(int id) {
        accountService.delete(id);
    }

    private void retrieveAllClients() {
        List<User> users = userListProducer.getUsers();
        for (User user : users) {
            if (user.getRole().equals(Role.CLIENT)) {
                clients.add(user);
            }
        }
    }

    public List<User> getClients() {
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }

    public Account getAddedAccount() {
        return addedAccount;
    }

    public void setAddedAccount(Account addedAccount) {
        this.addedAccount = addedAccount;
    }
}
