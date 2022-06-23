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

    public void addMoney(Money money) throws WalletException {
        if (money.amount == 0 || money.amount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        this.amount = this.amount + money.currencyCode.convertToBaseCurrency(money.amount);
    }

    public void withdrawMoney(Money withdrawalMoney) throws WalletException {
        if (withdrawalMoney.amount == 0 || withdrawalMoney.amount < 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        if (this.amount < withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount)) {
            throw new WalletException(WalletExceptionMessage.NOT_ENOUGH_BALANCE);
        }
        this.amount = this.amount - withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount);
    }

    public static double moneyInPreferredCurrency(Money money, CurrencyCode currencyCode) {
        if (currencyCode == CurrencyCode.USD) {
            return (currencyCode.convertToUSD(money.amount));
        }
        return money.amount;
    }
}
