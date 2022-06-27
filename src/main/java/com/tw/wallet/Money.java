package com.tw.wallet;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Money {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public double amount;
    public CurrencyCode currencyCode;

    public Money(double amount, CurrencyCode currencyCode){
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public static Money createMoney(double amount, CurrencyCode currencyCode){
        return new Money(amount, currencyCode);
    }

    public Money addMoney(Money money) throws WalletException {
        if (money.amount <= 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }

        double newAmount = this.currencyCode.convertToBaseCurrency(this.amount) + money.currencyCode.convertToBaseCurrency(money.amount);
        return new Money(formatDouble(newAmount), CurrencyCode.INR);

    }

    public Money withdrawMoney(Money withdrawalMoney) throws WalletException {
        if (withdrawalMoney.amount <= 0) {
            throw new WalletException(WalletExceptionMessage.INVALID_INPUT);
        }
        if (this.currencyCode.convertToBaseCurrency(this.amount) < withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount)) {
            throw new WalletException(WalletExceptionMessage.NOT_ENOUGH_BALANCE);
        }
        double newAmount = this.currencyCode.convertToBaseCurrency(this.amount) - withdrawalMoney.currencyCode.convertToBaseCurrency(withdrawalMoney.amount);
        return new Money(formatDouble(newAmount), CurrencyCode.INR);
    }

    private double formatDouble(double value){
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(decimalFormat.format(value));
    }

    public static double moneyInPreferredCurrency(Money money, CurrencyCode currencyCode) {
        if (currencyCode == CurrencyCode.USD) {
            return (currencyCode.convertToUSD(money.amount));
        }
        return money.amount;
    }
}
