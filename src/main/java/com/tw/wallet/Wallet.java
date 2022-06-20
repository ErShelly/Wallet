package com.tw.wallet;

public class Wallet {

    private double balance;

    private Wallet(double balance, CurrencyCode currencyCode) {
        this.balance = currencyCode.convertToBaseCurrency(balance);
    }

    public static Wallet createWallet(double balance, CurrencyCode currencyCode) {
        return new Wallet(balance, currencyCode);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double transactionAmount, CurrencyCode transactionCurrency) throws WalletException {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        balance = balance + transactionCurrency.convertToBaseCurrency(transactionAmount);
    }

    public void withdraw(double transactionAmount, CurrencyCode transactionCurrency) throws WalletException {
        if (transactionAmount == 0 || transactionAmount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        if (balance < transactionCurrency.convertToBaseCurrency(transactionAmount)) {
            throw new WalletException(WalletExceptionMessage.NOT_ENOUGH_BALANCE);
        }
        balance = balance - transactionCurrency.convertToBaseCurrency(transactionAmount);
    }

    public double balanceInPreferredCurrency(double baseValue, CurrencyCode currencyCode) {
        if (currencyCode == CurrencyCode.USD) {
            return (currencyCode.convertToUSD(baseValue));
        }
        return baseValue;
    }
}
