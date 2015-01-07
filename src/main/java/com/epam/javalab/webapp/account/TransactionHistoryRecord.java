package com.epam.javalab.webapp.account;

public class TransactionHistoryRecord {
    private int id;
    private int startAcc;
    private int finalAcc;
    private long amount;
    private ExchangeRate currency;

    public TransactionHistoryRecord(int currentAccID, int targetAccID, long amount, String transactionCurrency) {
        this.startAcc = currentAccID;
        this.finalAcc = targetAccID;
        this.amount = amount;
        this.currency = new ExchangeRate(transactionCurrency);
    }

    public TransactionHistoryRecord() {

    }

    public int getStartAcc() {
        return startAcc;
    }

    public void setStartAcc(int startAcc) {
        this.startAcc = startAcc;
    }

    public int getFinalAcc() {
        return finalAcc;
    }

    public void setFinalAcc(int finalAcc) {
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
        if (finalAcc != that.finalAcc) return false;
        if (startAcc != that.startAcc) return false;
        if (currency != that.currency) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startAcc;
        result = 31 * result + finalAcc;
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
