package com.epam.javalab.webapp.producer;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.data.SessionBean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class AccountListProducer {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Inject
    private SessionBean sessionBean;


    private List<Account> accounts;

    @Named
    @Produces
    private List<Account> currentAccounts;

    @Named
    @Produces
    public List<Account> getAccounts() {
        if (accounts == null) {
            retrieveAllAccounts();
        }
        return accounts;
    }


    public List<Account> getCurrentAccounts() {
        if (currentAccounts == null) {
            retrieveCurrentUserAccounts(sessionBean.getUser().getId());
        }
        return currentAccounts;
    }

    private void retrieveCurrentUserAccounts(int id) {
        currentAccounts = accountDAO.findAllByUserID(id);
    }

    public void onCurrentAccountListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Account account) {
        retrieveCurrentUserAccounts(account.getOwner().getId());
    }

    public void onAccountListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Account account) {
        retrieveAllAccounts();
    }

    private void retrieveAllAccounts() {
        accounts = accountDAO.findAll();
    }
}
