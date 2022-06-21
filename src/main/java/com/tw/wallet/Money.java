package com.tw.wallet;

public class Money {
    public double amount;
    public CurrencyCode currencyCode;

    public Money(double amount, CurrencyCode currencyCode){
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public static Money createMoney(double amount, CurrencyCode currencyCode){
        return new Money(amount, currencyCode);
    }
}