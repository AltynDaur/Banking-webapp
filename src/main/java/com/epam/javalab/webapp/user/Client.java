package com.epam.javalab.webapp.user;

import java.util.List;

public class Client extends User {
    private List bankAccounts;

    public Client(String firstName, String password) {

        super(firstName, password);
    }
    public Client(){};

    public void setBankAccounts(List bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List getBankAccounts() {
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
