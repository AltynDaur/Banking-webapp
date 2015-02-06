package com.epam.javalab.webapp.account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private ExchangeRate exchangeRate;

    private LocalDateTime localDateTime;

    public TransactionHistoryRecord(Account currentAccID, Account targetAccID, long amount, ExchangeRate transactionCurrency, LocalDateTime localDateTime) {
        this.startAcc = currentAccID;
        this.finalAcc = targetAccID;
        this.amount = amount;
        this.exchangeRate = transactionCurrency;
        this.localDateTime = localDateTime;
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

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TransactionHistoryRecord)
                ? id == (((TransactionHistoryRecord) o).id)
                : (o == this);

    }

    @Override
    public int hashCode() {
        int result = id;
        return (startAcc.equals(null)) ? result : super.hashCode();

    }
}
