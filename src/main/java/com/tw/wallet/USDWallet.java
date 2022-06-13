package com.tw.wallet;

import org.omg.CORBA.DynAnyPackage.Invalid;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class USDWallet implements Wallet {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    public Double depositWalletTransaction(Double existingBalance, String depositCurrencyCode, Double depositAmount) {
        if(depositAmount == 0 || depositAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (depositCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double depositAmountInUSD = INRtoUSDConversion(depositAmount);
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInUSD));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public Double withdrawWalletTransaction(Double existingBalance, String withdrawalCurrencyCode, Double withdrawalAmount) throws CannotProceedException {
        if(withdrawalAmount == 0 || withdrawalAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (withdrawalCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double withdrawalAmountInUSD = INRtoUSDConversion(withdrawalAmount);
            if (existingBalance < withdrawalAmountInUSD) {
                throw new CannotProceedException("Not enough Balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInUSD));
        }
        return existingBalance - withdrawalAmount;
    }

    private Double INRtoUSDConversion(Double INRValue){
        return  INRValue / 78.14;
    }
}
