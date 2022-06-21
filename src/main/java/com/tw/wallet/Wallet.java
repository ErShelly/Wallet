package com.tw.wallet;

public class Wallet {

    private double balance;
    private CurrencyCode baseCurrency;

    private Wallet(Money money) {
        this.balance = money.currencyCode.convertToBaseCurrency(money.amount);
        this.baseCurrency = CurrencyCode.INR;
    }

    public static Wallet createWallet(Money money) {
        return new Wallet(money);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(Money depositMoney) throws WalletException {
        if (depositMoney.amount == 0 || depositMoney.amount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        balance = balance + depositMoney.currencyCode.convertToBaseCurrency(depositMoney.amount);
    }

    public void withdraw(Money withdrawalMoney) throws WalletException {
        if (withdrawalMoney.amount == 0 || withdrawalMoney.amount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        if (balance < withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount)) {
            throw new WalletException(WalletExceptionMessage.NOT_ENOUGH_BALANCE);
        }
        balance = balance - withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount);
    }

    public double balanceInPreferredCurrency(double baseValue, CurrencyCode currencyCode) {
        if (currencyCode == CurrencyCode.USD) {
            return (currencyCode.convertToUSD(baseValue));
        }
        return baseValue;
    }
}
