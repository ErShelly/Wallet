package com.tw.wallet;

//import java.util.*;

import org.junit.jupiter.api.Test;

import javax.naming.CannotProceedException;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    //INR Wallet Test
    @Test
    void shouldReturnMoney40INRWhenExistingMoneyIs30INRAndDepositMoneyIs10INR() {
        String existingCurrencyCode = "INR";
        INRWallet inrWallet = new INRWallet();
        Double actualAmount = inrWallet.depositWalletTransaction(30.00, "INR", 10.00);
        assertEquals(40, actualAmount);
    }

    @Test
    void shouldReturnMoney20INRWhenExistingMoneyIs30INRAndWithdrawalMoneyIs10INR() throws CannotProceedException {
        String existingCurrencyCode = "INR";
        INRWallet inrWallet = new INRWallet();
        Double actualAmount = inrWallet.withdrawWalletTransaction(30.00, "INR", 10.00);
        assertEquals(20, actualAmount);
    }

    @Test
    void shouldReturnMoney32dot55INRWhenExistingMoneyIs30INRAndDepositMoneyIs20USD() {
        String existingCurrencyCode = "INR";
        INRWallet inrWallet = new INRWallet();
        Double actualAmount = inrWallet.depositWalletTransaction(30.00, "USD", 20.00);
        assertEquals(1592.8, actualAmount);
    }

    @Test
    void shouldReturn3dot86WhenWalletHas74dot85INRAnd1USDAnd149dot7INRWithPreferredCurrencyUSD() {
        Double walletBalance = 0.00;

        String preferredCurrencyCode = "USD";
        USDWallet usdWallet = new USDWallet();
        walletBalance = usdWallet.depositWalletTransaction(walletBalance, "INR", 74.85);
        walletBalance = usdWallet.depositWalletTransaction(walletBalance, "USD", 1.00);
        walletBalance = usdWallet.depositWalletTransaction(walletBalance, "INR", 149.7);
        assertEquals(3.86, walletBalance);
    }

    //USD Wallet Test

    @Test
    void shouldReturnMoney60USDWhenExistingMoneyIs20USDAndDepositMoneyIs40USD() {
        String existingCurrencyCode = "USD";
        USDWallet usdWallet = new USDWallet();
        Double actualAmount = usdWallet.depositWalletTransaction(30.00, "USD", 10.00);
        assertEquals(40, actualAmount);
    }

    @Test
    void shouldReturnMoney30USDWhenExistingMoneyIs40USDAndWithdrawalMoneyIs10USD() throws CannotProceedException {
        String existingCurrencyCode = "USD";
        USDWallet usdWallet = new USDWallet();
        Double actualAmount = usdWallet.withdrawWalletTransaction(30.00, "USD", 10.00);
        assertEquals(20, actualAmount);
    }

    @Test
    void shouldReturnMoney1592dot80USDWhenExistingMoneyIs30USDAndDepositMoneyIs2000INR() {
        String existingCurrencyCode = "USD";
        USDWallet usdWallet = new USDWallet();
        Double actualAmount = usdWallet.depositWalletTransaction(30.00, "INR", 2000.00);
        assertEquals(55.59, actualAmount);
    }

    @Test
    void shouldThrowExceptionWhenExistingMoneyIs30USDAndWithdrawalMoneyIs40USD() {
        try {
            String existingCurrencyCode = "USD";
            USDWallet usdWallet = new USDWallet();
            Double actualAmount = usdWallet.withdrawWalletTransaction(30.00, "USD", 40.00);
        } catch (CannotProceedException ex) {
            assertEquals("Not enough Balance.", ex.getMessage());
        }
    }

    @Test
    void shouldReturnMoney18dot6USDWhenExistingMoneyIs800USDAndWithdrawalMoneyIs200INR() throws CannotProceedException {
        String existingCurrencyCode = "USD";
        USDWallet usdWallet = new USDWallet();
        Double actualAmount = usdWallet.withdrawWalletTransaction(800.00, "INR", 200.00);
        assertEquals(797.44, actualAmount);
    }

    @Test
    void shouldReturn128dot14WhenWalletHas50INRAnd1USDWithPreferredCurrencyINR() {
        Double walletBalance = 0.00;

        INRWallet inrWallet = new INRWallet();
        walletBalance = inrWallet.depositWalletTransaction(walletBalance, "INR", 50.00);
        walletBalance = inrWallet.depositWalletTransaction(walletBalance, "USD", 1.00);
        assertEquals(128.14, walletBalance);
    }
}