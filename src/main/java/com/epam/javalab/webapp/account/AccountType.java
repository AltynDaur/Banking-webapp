package com.epam.javalab.webapp.account;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACCOUNTSTYPE")
public class AccountType {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column(name = "ACCNAME")
    /*@NotNull
    @Size(min = 3, max = 50)*/
    private String name;
    @Column(name = "PERCENT")
    /*@NotNull
    @Max(100)*/
    private int percent;
    @Column(name = "PERIOD")
    /*@NotNull
    @Max(100)*/
    private int period;
    @OneToOne
    /*@NotNull*/
    private ExchangeRate exchangeRate;

    public AccountType(String accTypeName, int percent, int period, ExchangeRate exchangeRate) {
        this.name = accTypeName;
        this.percent = percent;
        this.period = period;
        this.exchangeRate = exchangeRate;
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

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setExchangeRate(String currency) {
        this.exchangeRate = new ExchangeRate(currency);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AccountType)
                ? id == (((AccountType) o).id)
                : (o == this);

    }

    @Override
    public int hashCode() {
        int result = id;
        return (name.equals(null)) ? result : super.hashCode();

    }
}
