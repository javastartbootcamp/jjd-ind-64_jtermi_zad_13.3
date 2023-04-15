package pl.javastart.task;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> productList;
        List<CurrencyData> currencyRates;

        File fileOfCurrencies = new File("src/main/resources/currencies.csv");
        File fileOfProducts = new File("src/main/resources/products.csv");

        currencyRates = ListUtils.readCurrenciesToList(fileOfCurrencies);
        productList = ListUtils.readProductsToList(fileOfProducts);

        ListUtils.calculateEurPrice((ArrayList<Product>) productList, (ArrayList<CurrencyData>) currencyRates);

        BigDecimal sumOfAllProductsInEur = ListUtils.calculatePriceOfAllProductsInEur((ArrayList<Product>) productList,
                (ArrayList<CurrencyData>) currencyRates);
        System.out.println("Suma wszystkich produktów w EUR wynosi: " + sumOfAllProductsInEur);

        BigDecimal averagePriceInEur = ListUtils.printAverageValueOfProducts((ArrayList<Product>) productList,
                (ArrayList<CurrencyData>) currencyRates);
        System.out.println("Średnia wartość produktu w EUR wynosi: " + averagePriceInEur);

        Product mostExpensiveProduct = ListUtils.findMostExpensiveProduct((ArrayList<Product>) productList,
                (ArrayList<CurrencyData>) currencyRates);
        System.out.println("Najdroższy produkt w przeliczeniu na EUR to: " + mostExpensiveProduct.getName()
                + " a jego cena to: " + mostExpensiveProduct.getPriceInEur());

        Product lessExpensiveProduct = ListUtils.findLessExpensiveProduct(((ArrayList<Product>) productList),
                (ArrayList<CurrencyData>) currencyRates);
        System.out.println("Najdroższy produkt w przeliczeniu na EUR to: " + lessExpensiveProduct.getName()
                + " a jego cena to: " + lessExpensiveProduct.getPriceInEur());

    }
}
