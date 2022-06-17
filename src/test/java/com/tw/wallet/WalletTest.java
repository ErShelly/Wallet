package com.tw.wallet;

//import java.util.*;

import org.junit.jupiter.api.Test;

import javax.naming.CannotProceedException;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    //INR Wallet Test
    @Test
    void shouldReturn40INRWhenBalanceIs30INRAndDepositIs10INR() {
        Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
        wallet.deposit(10d, CurrencyCode.INR);

        assertEquals(40, wallet.getBalance());
    }

    @Test
    void shouldReturn20INRWhenBalanceIs30INRAndWithdrawIs10INR() throws CannotProceedException {
        Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
        wallet.withdraw(10d, CurrencyCode.INR);

        assertEquals(20, wallet.getBalance());
    }

    @Test
    void shouldReturn32dot55INRWhenBalanceIs30INRAndDepositIs20USD() {
        Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
        wallet.deposit(20d, CurrencyCode.USD);

        assertEquals(1592.8, wallet.getBalance());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
            wallet.deposit(0d, CurrencyCode.USD);
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
            wallet.deposit(-8.0d, CurrencyCode.USD);
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20INRAndWithdrawIs40INR() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createWallet(30d, CurrencyCode.INR);
            wallet.withdraw(40d, CurrencyCode.INR);
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20INRAndWithdrawIs40USD() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createWallet(20d, CurrencyCode.USD);
            wallet.withdraw(40d, CurrencyCode.USD);
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldReturn3dot86USDWhenWalletHas74dot85INRAnd1USDAnd149dot7INR() {
        double walletBalance = 0d;

        String preferredCurrencyCode = "USD";
        Wallet wallet = Wallet.createWallet(walletBalance, CurrencyCode.USD);

        wallet.deposit(74.85, CurrencyCode.INR);
        wallet.deposit(1d, CurrencyCode.USD);
        wallet.deposit(149.7, CurrencyCode.INR);

        double balanceInUSD = wallet.balanceInPreferredCurrency(wallet.getBalance(), CurrencyCode.USD);
        assertEquals(3.87, balanceInUSD);
    }

    @Test
    void shouldReturn546dot98INRWhenBalanceIs2USDAndDepositIs3USD() {
        Wallet wallet = Wallet.createWallet(2d, CurrencyCode.USD);
        wallet.deposit(3d, CurrencyCode.USD);

        assertEquals(390.7, wallet.getBalance());
    }

    @Test
    void shouldReturn781dot4WhenBalanceIs20USDAndWithdrawIs10USD() throws CannotProceedException {
        Wallet wallet = Wallet.createWallet(20d, CurrencyCode.USD);
        wallet.withdraw(10d, CurrencyCode.USD);

        assertEquals(781.4, wallet.getBalance());
    }

    @Test
    void shouldReturnMoney254dot42WhenBalanceIs3USDAndDepositIs20INR() {
        Wallet wallet = Wallet.createWallet(3d, CurrencyCode.USD);
        wallet.deposit(20d, CurrencyCode.INR);

        assertEquals(254.42, wallet.getBalance());
    }

    @Test
    void shouldReturn212dot56WhenBalanceIs4USDAndWithdrawalIs100INR() throws CannotProceedException {
        Wallet wallet = Wallet.createWallet(4d, CurrencyCode.USD);
        wallet.withdraw(100d, CurrencyCode.INR);

        assertEquals(212.56, wallet.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20USDAndWithdrawIs40USD() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createWallet(20d, CurrencyCode.USD);
            wallet.withdraw(40d, CurrencyCode.USD);
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20USDAndWithdrawIs2000INR() {
        Throwable exception = assertThrows(CannotProceedException.class, () -> {
            Wallet wallet = Wallet.createWallet(20d, CurrencyCode.USD);
            wallet.withdraw(2000d, CurrencyCode.INR);
        });

        assertEquals("Transaction cannot be completed: Not enough balance.", exception.getMessage());
    }

    @Test
    void shouldReturn128dot14INRWhenWalletHas50INRAnd1USD() {
        double walletBalance = 0d;

        String preferredCurrencyCode = "USD";
        Wallet wallet = Wallet.createWallet(walletBalance, CurrencyCode.USD);

        wallet.deposit(50d, CurrencyCode.INR);
        wallet.deposit(1d, CurrencyCode.USD);

        assertEquals(128.14, wallet.getBalance());
    }
}