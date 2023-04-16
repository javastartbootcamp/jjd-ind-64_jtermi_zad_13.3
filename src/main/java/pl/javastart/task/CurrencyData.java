package pl.javastart.task;

import java.math.BigDecimal;

public class CurrencyData {

    private final String name;
    private final BigDecimal exchangeRate;

    public CurrencyData(String name, BigDecimal exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

}
