package com.tw.wallet;

public class Currency {
    private final String displayName;
    private final String symbol;
    private final double baseFactor;

    public static final Currency INR = new Currency("Indian Rupee", "₹", 1);//base currency

    public static final Currency USD = new Currency("US Dollar", "$", 78.14);

    private Currency(String displayName, String symbol, double baseFactor) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.baseFactor = baseFactor;
    }

    private double convertToBaseCurrency(Double value){
        return value * baseFactor;
    }

}
