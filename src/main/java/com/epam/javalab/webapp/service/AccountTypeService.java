package com.epam.javalab.webapp.service;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;


@Stateless
public class AccountTypeService {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Inject
    private Event<AccountType> accountTypeEvent;

    public void add(AccountType addedAccountType) {
        accountTypeDAO.add(addedAccountType);
        accountTypeEvent.fire(addedAccountType);
    }

    public void delete(int id) {
        accountTypeDAO.delete(id);
        accountTypeEvent.fire(new AccountType());
    }

    public void update(AccountType accountType) {
        accountTypeDAO.update(accountType);
        accountTypeEvent.fire(accountType);
    }
}
