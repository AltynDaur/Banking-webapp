package com.epam.javalab.webapp.account;


import com.epam.javalab.webapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User client;
    @OneToOne
    private AccountType acctype;
    @NotNull
    private long amount;


    public Account(User owner, AccountType accType, long amount) {
        this.client = owner;
        this.acctype = accType;
        this.amount = amount;
    }
    public Account(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return client;
    }

    public void setOwner(User owner) {
        this.client = owner;
    }

    public AccountType getAcctype() {
        return acctype;
    }

    public void setAcctype(AccountType acctype) {
        this.acctype = acctype;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }



}
