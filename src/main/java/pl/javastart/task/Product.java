package pl.javastart.task;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private String currency;

    private BigDecimal priceInEur;

    public Product(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(BigDecimal priceInEur) {
        this.priceInEur = priceInEur;
    }

}
