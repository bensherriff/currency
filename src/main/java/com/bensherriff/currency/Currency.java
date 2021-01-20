package com.bensherriff.currency;

import java.math.BigDecimal;
import java.util.Objects;

public class Currency {

    private final long majorValue;
    private final long minorValue;
    private final CurrencyCode currencyCode;
    private final boolean negative;

    private Currency(long majorValue, long minorValue) {
        this(CurrencyCode.USD, majorValue < 0, majorValue, minorValue);
    }

    private Currency(CurrencyCode currencyCode, long majorValue, long minorValue) {
        this(currencyCode, majorValue < 0, majorValue, minorValue);
    }

    private Currency(CurrencyCode currencyCode, boolean negative, long majorValue, long minorValue) {
        this.currencyCode = currencyCode;
        this.negative = negative;
        this.majorValue = Math.abs(majorValue);
        this.minorValue = Math.abs(minorValue);
    }

    /**
     *
     * @param value
     * @return
     */
    public static Currency valueOf(double value) {
        return new Currency(majorValueFromDouble(value), minorValueFromDouble(value));
    }

    public static Currency valueOf(CurrencyCode currencyCode, double value) {
        return new Currency(currencyCode, majorValueFromDouble(value), minorValueFromDouble(value));
    }

    public Currency add(double value) {
        return new Currency(0, 0);
    }

    public Currency add(Currency currency) {
        return new Currency(0, 0);
    }

    public Currency subtract(double value) {
        return new Currency(0, 0);
    }

    public Currency subtract(Currency currency) {
        return new Currency(0, 0);
    }

    public Currency multiplyBy(double value) {
        return new Currency(0, 0);
    }

    public Currency multiplyBy(Currency currency) {
        return new Currency(0, 0);
    }

    public Currency divideBy(double value) throws CurrencyException {
        if (value == 0) {
            throw new CurrencyException("Cannot divide by zero");
        }
        return new Currency(0, 0);
    }

    public Currency divideBy(Currency currency) throws CurrencyException {
        if (currency.majorValue == 0 || currency.minorValue == 0) {
            throw new CurrencyException("Cannot divide by zero");
        }
        return new Currency(0, 0);
    }

    public CurrencyCode getSign() {
        return this.currencyCode;
    }

    public boolean isNegative() {
        return this.negative;
    }

    /**
     * Convert the currency to another currency with a given rate.
     * @param currencyCode The new currency to be exchanged into.
     * @param exchangeRate The rate at which the current currency is exchanged into the new currency.
     * @return The new currency.
     */
    public Currency exchangeTo(CurrencyCode currencyCode, double exchangeRate) {
        double value = this.majorValue;
        if (minorValue != 0) {
            value += (((double) this.minorValue)/100);
        }
        double updatedValue = value * exchangeRate;
        return new Currency(
                currencyCode,
                isNegative(),
                majorValueFromDouble(updatedValue),
                minorValueFromDouble(updatedValue));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(this.currencyCode)) {
            stringBuilder.append(this.currencyCode.toString()).append(" ");
        }
        if (this.negative) {
            stringBuilder.append("-");
        }
        stringBuilder.append(this.majorValue).append(".");
        if (this.minorValue < 10) {
            stringBuilder.append("0").append(String.valueOf(this.minorValue).charAt(0));
        } else {
            stringBuilder.append(String.valueOf(this.minorValue).charAt(0)).append(String.valueOf(this.minorValue).charAt(1));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return majorValue == currency.majorValue &&
                minorValue == currency.minorValue &&
                negative == currency.negative &&
                currencyCode == currency.currencyCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(majorValue, minorValue, currencyCode, negative);
    }

    private static long majorValueFromDouble(double value) {
        return BigDecimal.valueOf(value).longValue();
    }

    private static long minorValueFromDouble(double value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        long longValue = majorValueFromDouble(value);
        return bigDecimal.subtract(new BigDecimal(longValue)).multiply(new BigDecimal(100)).longValue();
    }
}
