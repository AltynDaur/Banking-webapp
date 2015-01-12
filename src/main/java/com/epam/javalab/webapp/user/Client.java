package com.epam.javalab.webapp.user;

import com.epam.javalab.webapp.account.Account;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client extends User {
    @OneToMany(targetEntity = Account.class, mappedBy = "client", cascade = CascadeType.ALL)
    //@JoinTable(name = "CLIENT_ACCOUNTS", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private List<Account> bankAccounts;

    public Client(String firstName, String password) {

        super(firstName, password);
    }

    public Client() {
    }

    public void setBankAccounts(List<Account> bankAccounts) {
        while (bankAccounts.iterator().hasNext()) {
            bankAccounts.iterator().next().setOwner(this);
        }
        this.bankAccounts = bankAccounts;
    }


    public List<Account> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (bankAccounts != null ? !bankAccounts.equals(client.bankAccounts) : client.bankAccounts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bankAccounts != null ? bankAccounts.hashCode() : 0;
    }
}
