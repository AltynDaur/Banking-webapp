package com.epam.javalab.webapp.account;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EXCHANGERATESTOTENGE", uniqueConstraints = @UniqueConstraint(columnNames = "currency"))
public class ExchangeRate {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private int id;

    @NotNull
    @Column(name = "CURRENCY")
    private String currency;

    @NotNull
    @Column(name = "VALUE")
    private  int value;

    public ExchangeRate(String currency, int exchangeValue) {
        this.currency=currency;
        this.value = exchangeValue;
    }

    public ExchangeRate() {
    }

    public ExchangeRate(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
