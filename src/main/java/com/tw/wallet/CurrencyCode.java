package com.tw.wallet;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrencyCode {
    DecimalFormat decimalFormat = new DecimalFormat("##.##");
    private final String displayName;
    private final String symbol;
    private final double baseFactor;

    public static final CurrencyCode INR = new CurrencyCode("Indian Rupee", "₹", 1);//base currency

    public static final CurrencyCode USD = new CurrencyCode("US Dollar", "$", 78.14);

    private CurrencyCode(String displayName, String symbol, double baseFactor) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.baseFactor = baseFactor;
    }

    public double convertToBaseCurrency(double value){
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return Double.valueOf(decimalFormat.format(value * baseFactor));
    }

    public double convertToUSD(double value){
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return Double.valueOf(decimalFormat.format(value / baseFactor));
    }

}
