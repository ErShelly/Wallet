package com.tw.wallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void shouldReturn40INRWhenMoneyIs30INRAndAddMoneyIs10INR() throws WalletException {
        Money money30 = Money.createMoney(30d, CurrencyCode.INR);
        Money money40 = Money.createMoney(10d, CurrencyCode.INR);

        money30.addMoney(money40);

        assertEquals(40, money30.amount);
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
}