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
        if (depositCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double depositAmountInUSD = depositAmount / 78.14;
            return Double.valueOf(decimalFormat.format(existingBalance + depositAmountInUSD));
        }
        return existingBalance + depositAmount;
    }

    @Override
    public Double withdrawWalletTransaction(Double existingBalance, String withdrawalCurrencyCode, Double withdrawalAmount) throws CannotProceedException {
        if (withdrawalCurrencyCode == "INR") {
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            Double withdrawalAmountInUSD = withdrawalAmount / 78.14;
            if (existingBalance < withdrawalAmountInUSD) {
                throw new CannotProceedException("Not enough Balance.");
            }
            return Double.valueOf(decimalFormat.format(existingBalance - withdrawalAmountInUSD));
        }
        return existingBalance - withdrawalAmount;
    }
}
