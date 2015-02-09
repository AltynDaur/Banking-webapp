package com.epam.javalab.webapp.service;


import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@RequestScoped
@Stateless
public class AccountService {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Inject
    private Event<Account> accountEvent;

    @Inject
    private Event<TransactionHistoryRecord> transactionHistoryRecordEvent;


    public void add(Account account) {
        account.setLocalDateTime(LocalDateTime.now());
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

    public Account findByID(int id) {
        return accountDAO.findByID(id);
    }

    public boolean transactionBtwnAccounts(Account startAcc, Account finalAcc, long amount) {
        accountDAO.transaction(startAcc, finalAcc, amount);
        accountEvent.fire(startAcc);
        transactionHistoryRecordEvent.fire(new TransactionHistoryRecord());
        return true;
    }
}
