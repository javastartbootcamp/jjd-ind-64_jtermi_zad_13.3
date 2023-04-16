package pl.javastart.task;

import java.math.BigDecimal;

public class ProductWithEurPrices extends Product {

    private BigDecimal eurPrice;

    public ProductWithEurPrices(Product product, BigDecimal eurPrice) {
        super(product.getName(), product.getPrice(), product.getCurrency());
        this.eurPrice = eurPrice;
    }

    public ProductWithEurPrices() {
    }

    public BigDecimal getEurPrice() {
        return eurPrice;
    }

}
