package com.tw.wallet;

import com.tw.wallet.exception.WalletException;

public class Wallet {

    private Money balance;

    private Wallet(Money money) {
        this.balance = money;
    }

    public static Wallet createWallet(Money money) {
        return new Wallet(money);
    }

    public double getBalance() {
        return balance.amount;
    }

    public void deposit(Money depositMoney) throws WalletException {
        balance = balance.addMoney(depositMoney);
    }

    public void withdraw(Money withdrawalMoney) throws WalletException {
        balance = balance.withdrawMoney(withdrawalMoney);
    }

    public double balanceInPreferredCurrency(Wallet wallet, CurrencyCode currencyCode) {
        return Money.moneyInPreferredCurrency(wallet.balance, currencyCode);
    }
}
