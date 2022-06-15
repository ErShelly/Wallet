package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.util.Currency;

public class Wallet {

    private final double walletBalance;
    private final double transactionAmount;
    private final CurrencyCode transactionCurrency;
    private final CurrencyCode walletBalanceCurrencyCode;
    private final double convertedWalletBalance;
    private final double convertedTransactionAmount;

    private Wallet(double walletBalance, CurrencyCode walletBalanceCurrencyCode, double transactionAmount, CurrencyCode transactionCurrency) {
        this.walletBalance = walletBalance;
        this.walletBalanceCurrencyCode = walletBalanceCurrencyCode;
        this.convertedWalletBalance = walletBalanceCurrencyCode.convertToBaseCurrency(walletBalance);
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
        this.convertedTransactionAmount = transactionCurrency.convertToBaseCurrency(transactionAmount);
    }

    public static Wallet createTransaction(double walletBalance, CurrencyCode walletBalanceCurrencyCode, double transactionAmount, CurrencyCode transactionCurrency) {
        return new Wallet(walletBalance, walletBalanceCurrencyCode, transactionAmount, transactionCurrency);
    }

    public double depositWalletTransaction() {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        return convertedWalletBalance + convertedTransactionAmount;
    }

    public double withdrawWalletTransaction() throws CannotProceedException {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        } else if (convertedWalletBalance < convertedTransactionAmount) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        return convertedWalletBalance - convertedTransactionAmount;
    }

    public double balanceInPreferredCurrency(double baseValue, CurrencyCode currencyCode){
        if (currencyCode == CurrencyCode.USD){
            return (currencyCode.convertToUSD(baseValue));
        }
        return baseValue;
    }
}
