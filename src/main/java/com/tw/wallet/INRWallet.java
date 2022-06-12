package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class INRWallet implements Wallet {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    public Double depositWalletTransaction(Double existingBalance, String depositCurrencyCode, Double depositAmount) {
        if (depositCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double depositAmountInINR = depositAmount * 78.14;
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInINR));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public Double withdrawWalletTransaction(Double existingBalance, String withdrawalCurrencyCode, Double withdrawalAmount) throws CannotProceedException {
        if (withdrawalCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double withdrawalAmountInINR = withdrawalAmount * 78.14;
            if (existingBalance < withdrawalAmountInINR) {
                throw new CannotProceedException("Not enough Balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInINR));
        }
        return existingBalance - withdrawalAmount;
    }
}
