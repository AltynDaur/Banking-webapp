package com.epam.javalab.webapp.account;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private double value;

    private LocalDateTime localDateTime;

    public ExchangeRate(String currency, int exchangeValue, LocalDateTime localDateTime) {
        this.currency = currency;
        this.value = exchangeValue;
        this.localDateTime = localDateTime;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

        ExchangeRate that = (ExchangeRate) o;

        if (id != that.id) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (localDateTime != null ? !localDateTime.equals(that.localDateTime) : that.localDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }
}
