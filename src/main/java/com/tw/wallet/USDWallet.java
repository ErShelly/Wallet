package com.tw.wallet;

import org.omg.CORBA.DynAnyPackage.Invalid;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.naming.CannotProceedException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class USDWallet implements Wallet {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    public double depositWalletTransaction(double existingBalance, String depositCurrencyCode, double depositAmount) {
        if(depositAmount == 0 || depositAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }

        if (depositCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            double depositAmountInUSD = INRtoUSDConversion(depositAmount);
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInUSD));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public double withdrawWalletTransaction(double existingBalance, String withdrawalCurrencyCode, double withdrawalAmount) throws CannotProceedException {
        if(withdrawalAmount == 0 || withdrawalAmount < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        if (withdrawalCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            double withdrawalAmountInUSD = INRtoUSDConversion(withdrawalAmount);
            if (existingBalance < withdrawalAmountInUSD) {
                throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInUSD));
        } else if (existingBalance < withdrawalAmount) {
            throw new CannotProceedException("Transaction cannot be completed: Not enough balance.");
        }
        return existingBalance - withdrawalAmount;
    }

    private double INRtoUSDConversion(double INRValue){
        return  INRValue / 78.14;
    }
}
