package com.epam.javalab.webapp.account;

public class ExchangeRate {
    private int id;
    private String currency;
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
