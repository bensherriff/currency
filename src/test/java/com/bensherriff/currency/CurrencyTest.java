package com.bensherriff.currency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testToString() {
        Assert.assertEquals("USD 100.00", Currency.valueOf(100).toString());
        Assert.assertEquals("USD -100.00", Currency.valueOf(-100).toString());
        Assert.assertEquals("USD 100.98", Currency.valueOf(100.98).toString());
        Assert.assertEquals("USD -100.98", Currency.valueOf(-100.98).toString());
        Assert.assertEquals("USD 100.82", Currency.valueOf(100.8217381).toString());
        Assert.assertEquals("USD -100.82", Currency.valueOf(-100.8217381).toString());
        Assert.assertEquals("USD 100.08", Currency.valueOf(100.08).toString());
        Assert.assertEquals("USD -100.08", Currency.valueOf(-100.08).toString());
    }

    @Test
    public void testAddition() {

    }

    @Test
    public void testSubtraction() {

    }

    @Test
    public void testMultiplication() {

    }

    @Test
    public void testDivision() {

    }

    @Test
    public void testExchangeTo() {
        Currency exchangedCurrency = Currency.valueOf(CurrencyCode.USD, 100).
                exchangeTo(CurrencyCode.EUR, 0.82350);
        Currency expectedCurrency = Currency.valueOf(CurrencyCode.EUR, 82.35);
        Assert.assertEquals(expectedCurrency, exchangedCurrency);

        exchangedCurrency = Currency.valueOf(CurrencyCode.USD, 81372.18).
                exchangeTo(CurrencyCode.EUR, 0.82350);
        expectedCurrency = Currency.valueOf(CurrencyCode.EUR, 67009.99023);
        Assert.assertEquals(expectedCurrency, exchangedCurrency);
    }
}
