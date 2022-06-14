package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.util.Currency;

public class Wallet {

    private final double existingBalance;
    private final double transactionAmount;
    private final CurrencyCode transactionCurrency;
    private final CurrencyCode existingBalanceCurrencyCode;
    private final double convertedExistingBalance;
    private final double convertedTransactionAmount;

    private Wallet(double existingBalance, CurrencyCode existingBalanceCurrencyCode, double transactionAmount, CurrencyCode transactionCurrency) {
        this.existingBalance = existingBalance;
        this.existingBalanceCurrencyCode = existingBalanceCurrencyCode;
        this.convertedExistingBalance = existingBalanceCurrencyCode.convertToBaseCurrency(existingBalance);
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
        this.convertedTransactionAmount = transactionCurrency.convertToBaseCurrency(transactionAmount);
    }

    public static Wallet createTransaction(double existingBalance, CurrencyCode existingBalanceCurrencyCode, double transactionAmount, CurrencyCode transactionCurrency) {
        return new Wallet(existingBalance, existingBalanceCurrencyCode, transactionAmount, transactionCurrency);
    }

    public double depositWalletTransaction() {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        return convertedExistingBalance + convertedTransactionAmount;
    }

    public double withdrawWalletTransaction() throws CannotProceedException {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new IllegalArgumentException("Invalid input");
        } else if (convertedExistingBalance < convertedTransactionAmount) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        return convertedExistingBalance - convertedTransactionAmount;
    }
}
