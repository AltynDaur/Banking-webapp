package com.epam.javalab.webapp.producer;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class AccountTypeListProducer {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    private List<AccountType> accountTypes;

    @Named
    @Produces
    public List<AccountType> getAccountTypes() {
        if (accountTypes == null) {
            retrieveAllAccountTypes();
        }
        return accountTypes;
    }

    private void retrieveAllAccountTypes() {
        accountTypes = accountTypeDAO.findAll();
    }

    public void onAccountListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final AccountType accountType) {
        retrieveAllAccountTypes();
    }

}
