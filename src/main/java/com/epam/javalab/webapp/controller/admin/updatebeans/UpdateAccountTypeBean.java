package com.epam.javalab.webapp.controller.admin.updatebeans;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.service.AccountTypeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean
public class UpdateAccountTypeBean {

    @Inject
    private AccountTypeService accountTypeService;

    private AccountType selectedAccountType;

    public String update(AccountType accountType) {
        accountTypeService.update(accountType);
        return "accountTypes?faces-redirect=true";
    }

    public void select(AccountType accountType) {
        selectedAccountType = new AccountType();
        selectedAccountType = accountType;
    }

    public boolean isSelected(AccountType accountType) {
        if (selectedAccountType == null)
            return false;
        else if (accountType == null)
            return false;
        else
            return accountType.equals(selectedAccountType);
    }
}
