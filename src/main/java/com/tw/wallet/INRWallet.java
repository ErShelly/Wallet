package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class INRWallet implements Wallet {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    public double depositWalletTransaction(double existingBalance, String depositCurrencyCode, double depositAmount) {
        if(depositAmount == 0 || depositAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (depositCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            double depositAmountInINR = USDtoINRConversion(depositAmount);
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInINR));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public double withdrawWalletTransaction(double existingBalance, String withdrawalCurrencyCode, double withdrawalAmount) throws CannotProceedException {
        if(withdrawalAmount == 0 || withdrawalAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (withdrawalCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            double withdrawalAmountInINR = USDtoINRConversion(withdrawalAmount);
            if (existingBalance < withdrawalAmountInINR) {
                throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInINR));
        } else if (existingBalance < withdrawalAmount) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        return existingBalance - withdrawalAmount;
    }

    private double USDtoINRConversion(double USDValue){
        return  USDValue * 78.14;
    }
}
