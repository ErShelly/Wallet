package com.tw.wallet;

//import java.util.*;

import org.junit.jupiter.api.Test;

import javax.naming.CannotProceedException;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    //INR Wallet Test
    @Test
    void shouldReturnMoney40INRWhenExistingMoneyIs30INRAndDepositMoneyIs10INR() {
        Wallet wallet = Wallet.createTransaction(30.00, CurrencyCode.INR, 10.00, CurrencyCode.INR);
        double actualAmount = wallet.depositWalletTransaction();

        assertEquals(40, actualAmount);
    }

    @Test
    void shouldReturnMoney20INRWhenExistingMoneyIs30INRAndWithdrawalMoneyIs10INR() throws CannotProceedException {
        Wallet wallet = Wallet.createTransaction(30.00, CurrencyCode.INR, 10.00, CurrencyCode.INR);
        double actualAmount = wallet.withdrawWalletTransaction();

        assertEquals(20, actualAmount);
    }

    @Test
    void shouldReturnMoney32dot55INRWhenExistingMoneyIs30INRAndDepositMoneyIs20USD() {
        Wallet wallet = Wallet.createTransaction(30.00, CurrencyCode.INR, 20.00, CurrencyCode.USD);
        double actualAmount = wallet.depositWalletTransaction();

        assertEquals(1592.8, actualAmount);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsZeroForINRWallet() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Wallet wallet = Wallet.createTransaction(30.00, CurrencyCode.INR, 0.00, CurrencyCode.INR);
            double actualAmount = wallet.depositWalletTransaction();
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsNegativeForINRWallet() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Wallet wallet = Wallet.createTransaction(30.00, CurrencyCode.INR, -8.00, CurrencyCode.INR);
            double actualAmount = wallet.depositWalletTransaction();
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenExistingMoneyIs20INRAndWithdrawalMoneyIs40INR() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createTransaction(20.00, CurrencyCode.INR, 40.00, CurrencyCode.INR);
            double actualAmount = wallet.withdrawWalletTransaction();
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenExistingMoneyIs20INRAndWithdrawalMoneyIs40USD() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createTransaction(20.00, CurrencyCode.INR, 40.00, CurrencyCode.USD);
            double actualAmount = wallet.withdrawWalletTransaction();
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    /*@Test
    void shouldReturn3dot86WhenWalletHas74dot85INRAnd1USDAnd149dot7INRWithPreferredCurrencyUSD() {
        double walletBalance = 0.00;

        String preferredCurrencyCode = "USD";

        Wallet transaction74dot85INR = Wallet.createTransaction(walletBalance,CurrencyCode.INR,40.00, CurrencyCode.INR);
        Wallet transaction1USD = Wallet.createTransaction(walletBalance,CurrencyCode.INR,1.00, CurrencyCode.USD);
        Wallet transaction149dot7 = Wallet.createTransaction(walletBalance,CurrencyCode.INR,149.7, CurrencyCode.INR);

        walletBalance = transaction74dot85INR.depositWalletTransaction() + transaction1USD.depositWalletTransaction() + transaction149dot7.depositWalletTransaction();
        assertEquals(3.86, walletBalance);
    }*/

    @Test
    void shouldReturnMoney546dot98INRWhenExistingMoneyIs2USDAndDepositMoneyIs4USD() {
        Wallet wallet = Wallet.createTransaction(2.00, CurrencyCode.USD, 5.00, CurrencyCode.USD);
        double actualAmount = wallet.depositWalletTransaction();
        assertEquals(546.98, actualAmount);
    }

    @Test
    void shouldReturnMoney781dot4WhenExistingMoneyIs20USDAndWithdrawalMoneyIs10USD() throws CannotProceedException {
        Wallet wallet = Wallet.createTransaction(20.00, CurrencyCode.USD, 10.00, CurrencyCode.USD);
        double actualAmount = wallet.withdrawWalletTransaction();

        assertEquals(781.4, actualAmount);
    }

    @Test
    void shouldReturnMoney254dot42WhenExistingMoneyIs3USDAndDepositMoneyIs20INR() {
        Wallet wallet = Wallet.createTransaction(3.00, CurrencyCode.USD, 20.00, CurrencyCode.INR);
        double actualAmount = wallet.depositWalletTransaction();

        assertEquals(254.42, actualAmount);
    }

    @Test
    void shouldReturnMoney212dot56WhenExistingMoneyIs4USDAndWithdrawalMoneyIs100INR() throws CannotProceedException {
        Wallet wallet = Wallet.createTransaction(4.00, CurrencyCode.USD, 100.00, CurrencyCode.INR);
        double actualAmount = wallet.withdrawWalletTransaction();

        assertEquals(212.56, actualAmount);
    }

    @Test
    void shouldThrowExceptionWhenExistingMoneyIs20USDAndWithdrawalMoneyIs40USD() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createTransaction(20.00, CurrencyCode.USD, 40.00, CurrencyCode.USD);
            double actualAmount = wallet.withdrawWalletTransaction();
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenExistingMoneyIs20USDAndWithdrawalMoneyIs2000INR() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createTransaction(20.00, CurrencyCode.USD, 2000.00, CurrencyCode.INR);
            double actualAmount = wallet.withdrawWalletTransaction();
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldReturn128dot14WhenWalletHas50INRAnd1USDWithPreferredCurrencyINR() {
        double walletBalance = 0.00;

        Wallet transaction50INR = Wallet.createTransaction(walletBalance, CurrencyCode.INR, 50.00, CurrencyCode.INR);
        Wallet transaction1USD = Wallet.createTransaction(walletBalance, CurrencyCode.INR, 1.00, CurrencyCode.USD);

        walletBalance = transaction50INR.depositWalletTransaction() + transaction1USD.depositWalletTransaction();
        assertEquals(128.14, walletBalance);
    }
}