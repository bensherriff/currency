package com.bensherriff.currency;

public enum CurrencyCode {

    USD("$", 840), // US Dollar
    EUR("€", 978), // Euro
    GBP("£", 826), // British Pound Sterling
    CAD("$", 124), // Canadian Dollar
    AUD("$", 036), // Australian Dollar
    JPY("¥", 392), // Japanese Yen
    INR("₨", 356), // Indian Rupee
    SGD("$", 703), // Singapore Dollar
    MYR("Rm", 458), // Malaysian Ringgit
    CNY("¥", 156), // Chinese Yuan
    RUB("₽", 643), // Russian Ruble
    CHF("Fr", 756); // Swiss Franc

    private final String symbol;
    private final long number;

    CurrencyCode(String symbol, long number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public long getNumber() {
        return this.number;
    }
}
