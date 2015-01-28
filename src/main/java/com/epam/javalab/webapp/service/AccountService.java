package com.epam.javalab.webapp.service;


import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@Stateless
public class AccountService {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Inject
    private Event<Account> accountEvent;


    public void add(Account account) {
        accountDAO.add(account);
        accountEvent.fire(account);
    }

    public void update(Account account) {
        accountDAO.update(account);
        accountEvent.fire(account);
    }

    public void delete(int id) {
        accountDAO.delete(id);
        accountEvent.fire(new Account());
    }
}
