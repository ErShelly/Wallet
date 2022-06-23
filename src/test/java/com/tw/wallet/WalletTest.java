package com.tw.wallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void shouldReturn40INRWhenBalanceIs30INRAndDepositIs10INR() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

        wallet.deposit(Money.createMoney(10d, CurrencyCode.INR));

        assertEquals(40, wallet.getBalance());
    }

    @Test
    void shouldReturn20INRWhenBalanceIs30INRAndWithdrawIs10INR() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

        wallet.withdraw(Money.createMoney(10d, CurrencyCode.INR));

        assertEquals(20, wallet.getBalance());
    }

    @Test
    void shouldReturn32dot55INRWhenBalanceIs30INRAndDepositIs20USD() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

        wallet.deposit(Money.createMoney(20d, CurrencyCode.USD));

        assertEquals(1592.8, wallet.getBalance());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsZero() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

            wallet.deposit(Money.createMoney(0d, CurrencyCode.USD));
        });

        assertEquals(WalletExceptionMessage.INVALID_INPUT, exception.getMessage());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenDepositAmountIsNegative() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

            wallet.deposit(Money.createMoney(-8.0d, CurrencyCode.USD));
        });

        assertEquals(WalletExceptionMessage.INVALID_INPUT, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20INRAndWithdrawIs40INR() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(30d, CurrencyCode.INR));

            wallet.withdraw(Money.createMoney(40d, CurrencyCode.INR));
        });

        assertEquals(WalletExceptionMessage.NOT_ENOUGH_BALANCE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20INRAndWithdrawIs40USD() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(20d, CurrencyCode.USD));

            wallet.withdraw(Money.createMoney(40d, CurrencyCode.USD));
        });

        assertEquals(WalletExceptionMessage.NOT_ENOUGH_BALANCE, exception.getMessage());
    }

    @Test
    void shouldReturn3dot86USDWhenWalletHas74dot85INRAnd1USDAnd149dot7INR() throws WalletException {
        double walletBalance = 0d;
        Wallet wallet = Wallet.createWallet(Money.createMoney(walletBalance, CurrencyCode.USD));

        wallet.deposit(Money.createMoney(74.85, CurrencyCode.INR));
        wallet.deposit(Money.createMoney(1d, CurrencyCode.USD));
        wallet.deposit(Money.createMoney(149.7, CurrencyCode.INR));

        double balanceInUSD = wallet.balanceInPreferredCurrency(wallet, CurrencyCode.USD);

        assertEquals(3.87, balanceInUSD);
    }

    @Test
    void shouldReturn546dot98INRWhenBalanceIs2USDAndDepositIs3USD() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(2d, CurrencyCode.USD));

        wallet.deposit(Money.createMoney(3d, CurrencyCode.USD));

        assertEquals(390.7, wallet.getBalance());
    }

    @Test
    void shouldReturn781dot4WhenBalanceIs20USDAndWithdrawIs10USD() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(20d, CurrencyCode.USD));

        wallet.withdraw(Money.createMoney(10d, CurrencyCode.USD));

        assertEquals(781.4, wallet.getBalance());
    }

    @Test
    void shouldReturnMoney254dot42WhenBalanceIs3USDAndDepositIs20INR() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(3d, CurrencyCode.USD));

        wallet.deposit(Money.createMoney(20d, CurrencyCode.INR));

        assertEquals(254.42, wallet.getBalance());
    }

    @Test
    void shouldReturn212dot56WhenBalanceIs4USDAndWithdrawalIs100INR() throws WalletException {
        Wallet wallet = Wallet.createWallet(Money.createMoney(4d, CurrencyCode.USD));

        wallet.withdraw(Money.createMoney(100d, CurrencyCode.INR));

        assertEquals(212.56, wallet.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20USDAndWithdrawIs40USD() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(20d, CurrencyCode.USD));

            wallet.withdraw(Money.createMoney(40d, CurrencyCode.USD));
        });

        assertEquals(WalletExceptionMessage.NOT_ENOUGH_BALANCE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIs20USDAndWithdrawIs2000INR() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Wallet wallet = Wallet.createWallet(Money.createMoney(20d, CurrencyCode.USD));

            wallet.withdraw(Money.createMoney(2000d, CurrencyCode.INR));
        });

        assertEquals(WalletExceptionMessage.NOT_ENOUGH_BALANCE, exception.getMessage());
    }

    @Test
    void shouldReturn128dot14INRWhenWalletHas50INRAnd1USD() throws WalletException {
        double walletBalance = 0d;
        Wallet wallet = Wallet.createWallet(Money.createMoney(walletBalance, CurrencyCode.USD));

        wallet.deposit(Money.createMoney(50d, CurrencyCode.INR));
        wallet.deposit(Money.createMoney(1d, CurrencyCode.USD));

        assertEquals(128.14, wallet.getBalance());
    }
}