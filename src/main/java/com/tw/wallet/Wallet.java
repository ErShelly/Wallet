package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.util.Currency;

public class Wallet {

    private double balance;
    private final CurrencyCode currencyCode;
    private double convertedWalletBalance;

    private Wallet(double balance, CurrencyCode currencyCode) {
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.convertedWalletBalance = currencyCode.convertToBaseCurrency(balance);
    }

    public static Wallet createWallet(double balance, CurrencyCode currencyCode) {
        return new Wallet(balance, currencyCode);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double transactionAmount, CurrencyCode transactionCurrency) {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        balance = convertedWalletBalance + transactionCurrency.convertToBaseCurrency(transactionAmount);
        convertedWalletBalance = balance;
    }

    public void withdraw(double transactionAmount, CurrencyCode transactionCurrency) throws CannotProceedException {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        } else if (convertedWalletBalance < transactionCurrency.convertToBaseCurrency(transactionAmount)) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        balance = convertedWalletBalance - transactionCurrency.convertToBaseCurrency(transactionAmount);
        convertedWalletBalance = balance;
    }

    public double balanceInPreferredCurrency(double baseValue, CurrencyCode currencyCode) {
        if (currencyCode == CurrencyCode.USD) {
            return (currencyCode.convertToUSD(baseValue));
        }
        return baseValue;
    }
}
