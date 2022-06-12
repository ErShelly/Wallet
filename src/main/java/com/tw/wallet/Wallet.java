package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.text.DecimalFormat;

public interface Wallet {
    public Double depositWalletTransaction(Double existingBalance, String depositCurrencyCode , Double depositAmount);
    public Double withdrawWalletTransaction(Double existingBalance, String withdrawalCurrencyCode , Double withdrawalAmount) throws CannotProceedException;
}
