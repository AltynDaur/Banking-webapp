package com.epam.javalab.webapp.controller.admin.updatedatabeans;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.service.AccountService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean
public class UpdateAccountBean {

    @Inject
    private AccountService accountService;

    private Account selectedAccount;

    public void update(Account account) {
        accountService.update(account);
        selectedAccount = null;
    }


    public void select(Account account) {
        selectedAccount = new Account();
        selectedAccount = account;
    }

    public boolean isSelected(Account account) {
        if (selectedAccount == null) {
            return false;
        } else if (account == null) {
            return false;
        } else
            return account.equals(selectedAccount);
    }


}
