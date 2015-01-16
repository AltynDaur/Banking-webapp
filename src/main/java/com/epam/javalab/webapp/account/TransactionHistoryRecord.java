package com.epam.javalab.webapp.account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSACTIONHISTORY")
public class TransactionHistoryRecord {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @NotNull
    @OneToOne
    private Account startAcc;
    @NotNull
    @OneToOne
    private Account finalAcc;
    @NotNull
    @Column
    private long amount;
    @NotNull
    @OneToOne
    @JoinColumn(name = "ID")
    private ExchangeRate currency;

    public TransactionHistoryRecord(Account currentAccID, Account targetAccID, long amount, ExchangeRate transactionCurrency) {
        this.startAcc = currentAccID;
        this.finalAcc = targetAccID;
        this.amount = amount;
        this.currency = transactionCurrency;
    }

    public TransactionHistoryRecord() {

    }

    public Account getStartAcc() {
        return startAcc;
    }

    public void setStartAcc(Account startAcc) {
        this.startAcc = startAcc;
    }

    public Account getFinalAcc() {
        return finalAcc;
    }

    public void setFinalAcc(Account finalAcc) {
        this.finalAcc = finalAcc;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionHistoryRecord that = (TransactionHistoryRecord) o;

        if (amount != that.amount) return false;
        if (id != that.id) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (finalAcc != null ? !finalAcc.equals(that.finalAcc) : that.finalAcc != null) return false;
        if (startAcc != null ? !startAcc.equals(that.startAcc) : that.startAcc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startAcc != null ? startAcc.hashCode() : 0);
        result = 31 * result + (finalAcc != null ? finalAcc.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
