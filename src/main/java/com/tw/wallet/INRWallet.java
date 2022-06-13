package com.tw.wallet;

import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class INRWallet implements Wallet {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    public Double depositWalletTransaction(Double existingBalance, String depositCurrencyCode, Double depositAmount) {
        if(depositAmount == 0 || depositAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (depositCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double depositAmountInINR = USDtoINRConversion(depositAmount);
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInINR));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public Double withdrawWalletTransaction(Double existingBalance, String withdrawalCurrencyCode, Double withdrawalAmount) throws CannotProceedException {
        if(withdrawalAmount == 0 || withdrawalAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (withdrawalCurrencyCode == "USD") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double withdrawalAmountInINR = USDtoINRConversion(withdrawalAmount);
            if (existingBalance < withdrawalAmountInINR) {
                throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInINR));
        }
        if (existingBalance < withdrawalAmount) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        return existingBalance - withdrawalAmount;
    }

    private Double USDtoINRConversion(Double USDValue){
        return  USDValue * 78.14;
    }
}
