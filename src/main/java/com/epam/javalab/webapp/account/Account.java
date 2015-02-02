package com.epam.javalab.webapp.account;


import com.epam.javalab.webapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @NotNull
    private User client;
    @OneToOne
    @NotNull
    private AccountType acctype;
    @NotNull
    private long amount;

    private LocalDateTime localDateTime;


    public Account(User owner, AccountType accType, long amount, LocalDateTime localDateTime) {
        this.client = owner;
        this.acctype = accType;
        this.amount = amount;
        this.localDateTime = localDateTime;
    }

    public Account() {
    }

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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (amount != account.amount) return false;
        if (id != account.id) return false;
        if (acctype != null ? !acctype.equals(account.acctype) : account.acctype != null) return false;
        if (client != null ? !client.equals(account.client) : account.client != null) return false;
        if (localDateTime != null ? !localDateTime.equals(account.localDateTime) : account.localDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (acctype != null ? acctype.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }
}
