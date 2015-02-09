package com.epam.javalab.webapp.controller.admin;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.service.AccountTypeService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@ManagedBean
public class AdminAccountTypesController {

    @Inject
    private AccountTypeService accountTypeService;

    @Named
    @Produces
    private AccountType addedAccountType;

    @PostConstruct
    private void init() {
        addedAccountType = new AccountType();
    }

    public String add() {
        accountTypeService.add(addedAccountType);
        return "accountTypesPage?faces-redirect=true";
    }

    public void delete(int id) {
        accountTypeService.delete(id);
    }

    public AccountType getAddedAccountType() {
        return addedAccountType;
    }

    public void setAddedAccountType(AccountType addedAccountType) {
        this.addedAccountType = addedAccountType;
    }
}