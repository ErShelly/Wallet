package com.tw.wallet;

public class Wallet {

    private Money balance;

    private Wallet(Money money) {
        this.balance = money;
    }

    public static Wallet createWallet(Money money) {
        money.amount = money.currencyCode.convertToBaseCurrency(money.amount);
        money.currencyCode = money.currencyCode.getBaseCurrency();
        return new Wallet(money);
    }

    public double getBalance() {
        return balance.amount;
    }

    public void deposit(Money depositMoney) throws WalletException {
        balance.addMoney(depositMoney);
    }

    public void withdraw(Money withdrawalMoney) throws WalletException {
        balance.withdrawMoney(withdrawalMoney);
    }

    public double balanceInPreferredCurrency(Wallet wallet, CurrencyCode currencyCode) {
        return Money.moneyInPreferredCurrency(wallet.balance, currencyCode);
    }
}
