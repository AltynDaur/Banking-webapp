package com.epam.javalab.webapp.controller.admin.updatedatabeans;

import com.epam.javalab.webapp.account.AccountType;
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
        selectedAccountType = null;
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
