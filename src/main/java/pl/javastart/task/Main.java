package pl.javastart.task;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> productList;
        List<CurrencyData> currencyRates;

        File fileOfCurrencies = new File("src/main/resources/currencies.csv");
        File fileOfProducts = new File("src/main/resources/products.csv");

        currencyRates = ListUtils.readCurrenciesToList(fileOfCurrencies);
        productList = ListUtils.readProductsToList(fileOfProducts);

        List<ProductWithEurPrices> productsWithEurPricesList = ListUtils.calculateEurPrice(productList, currencyRates);

        BigDecimal sumOfAllProductsInEur = ListUtils.calculatePriceOfAllProductsInEur(productsWithEurPricesList);
        System.out.println("Suma wszystkich produktów w EUR wynosi: " + sumOfAllProductsInEur);

        BigDecimal averagePriceInEur = ListUtils.calculateAverageValueOfProducts(productsWithEurPricesList);
        System.out.println("Średnia wartość produktu w EUR wynosi: " + averagePriceInEur);

        ProductWithEurPrices mostExpensiveProduct = ListUtils.findMostExpensiveProduct(productsWithEurPricesList);
        System.out.println("Najdroższy produkt w przeliczeniu na EUR to: " + mostExpensiveProduct.getName()
                + " a jego cena to: " + mostExpensiveProduct.getEurPrice());

        ProductWithEurPrices lessExpensiveProduct = ListUtils.findLessExpensiveProduct(productsWithEurPricesList);
        System.out.println("Najdroższy produkt w przeliczeniu na EUR to: " + lessExpensiveProduct.getName()
                + " a jego cena to: " + lessExpensiveProduct.getEurPrice());

    }
}
