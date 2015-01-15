package com.epam.javalab.webapp.account;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTSTYPE")
public class AccountType {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column(name = "ACCNAME")
    private String name;
    @Column(name = "PERCENT")
    private int percent;
    @Column(name = "PERIOD")
    private int period;
    @OneToOne
    private ExchangeRate currency;

    public AccountType(String accTypeName, int percent, int period, ExchangeRate exchangeRate) {
        this.name = accTypeName;
        this.percent = percent;
        this.period = period;
        this.currency = exchangeRate;
    }

    public AccountType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getCurrency() {
        return currency.getCurrency();
    }

    public void setCurrency(String currency) {
        this.currency = new ExchangeRate(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountType that = (AccountType) o;

        if (id != that.id) return false;
        if (percent != that.percent) return false;
        if (period != that.period) return false;
        if (currency != that.currency) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + percent;
        result = 31 * result + period;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
