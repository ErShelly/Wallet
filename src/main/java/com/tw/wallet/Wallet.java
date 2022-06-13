package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.text.DecimalFormat;

public interface Wallet {
    public double depositWalletTransaction(double existingBalance, String depositCurrencyCode , double depositAmount);
    public double withdrawWalletTransaction(double existingBalance, String withdrawalCurrencyCode , double withdrawalAmount) throws CannotProceedException;
}
