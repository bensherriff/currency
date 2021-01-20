package com.bensherriff.currency;

public class CurrencyException extends Exception {

    public CurrencyException(final Throwable t) {
        super(t);
    }

    public CurrencyException(final String s) {
        super(s);
    }

    public CurrencyException(final String s, final Throwable t) {
        super(s, t);
    }
}
