package com.tw.wallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void shouldReturn40INRWhenMoneyIs30INRAndAddMoneyIs10INR() throws WalletException {
        Money money30 = Money.createMoney(30d, CurrencyCode.INR);
        Money money10 = Money.createMoney(10d, CurrencyCode.INR);

        Money newMoney = money30.addMoney(money10);

        assertEquals(40, newMoney.amount);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenAddMoneyIsZero() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Money money30 = Money.createMoney(30d, CurrencyCode.INR);
            Money moneyZero = Money.createMoney(0d, CurrencyCode.USD);

            money30.addMoney(moneyZero);
        });

        assertEquals(WalletExceptionMessage.INVALID_INPUT, exception.getMessage());
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenAddMoneyIsNegative() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Money money20 = Money.createMoney(20d, CurrencyCode.INR);
            Money moneyNegative = Money.createMoney(-1d, CurrencyCode.USD);

            money20.addMoney(moneyNegative);
        });

        assertEquals(WalletExceptionMessage.INVALID_INPUT, exception.getMessage());
    }

    @Test
    void shouldReturn10INRWhenMoneyIs30USDAndWithdrawalMoneyIs20USD() throws WalletException {
        Money money30 = Money.createMoney(30d, CurrencyCode.USD);
        Money money20 = Money.createMoney(20d, CurrencyCode.USD);

        Money newMoney = money30.withdrawMoney(money20);

        assertEquals(781.39, newMoney.amount);
    }

    @Test
    void shouldThrowExceptionWhenMoneyIs20INRAndWithdrawIs40INR() {
        Throwable exception = assertThrows(WalletException.class, () -> {
            Money money30 = Money.createMoney(20d, CurrencyCode.INR);

            money30.withdrawMoney(Money.createMoney(40d, CurrencyCode.INR));
        });

        assertEquals(WalletExceptionMessage.NOT_ENOUGH_BALANCE, exception.getMessage());
    }
}