package com.epam.javalab.webapp.account;

import com.epam.javalab.webapp.user.User;

public class Account {
    private int id;
    private String owner;
    private String acctype;
    private long amount;
    private ExchangeRate currency;

    public Account(String ownerID,String accTypeID,long amount){
        this.owner = String.valueOf(ownerID);
        this.acctype = String.valueOf(accTypeID);
        this.amount = amount;
    }
    public Account(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency.getCurrency();
    }

    public void setCurrency(String currency) {
        this.currency = new ExchangeRate(currency);
    }


    public void setExchangeValue(int exchangeValue) {
        this.currency.setValue(exchangeValue);
    }

    public int getExchangeValue(){
        return currency.getValue();
    }

    public void setExchangeRate(String string, int anInt) {
        this.currency = new ExchangeRate(string,anInt);
    }
}
