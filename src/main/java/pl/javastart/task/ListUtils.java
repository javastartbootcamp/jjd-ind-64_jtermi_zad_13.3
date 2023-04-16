package pl.javastart.task;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static List<CurrencyData> readCurrenciesToList(File file) {
        List<String> listOfLines = FileHandling.readFileToList(file);
        return fillListOfCurrencies(listOfLines);
    }

    private static List<CurrencyData> fillListOfCurrencies(List<String> listOfLines) {

        List<CurrencyData> listOfCurrencies = new ArrayList<>();
        for (String listOfLine : listOfLines) {

            String[] currencySplit = listOfLine.split(";", 2);

            String currencyName = currencySplit[0];
            Double d = Double.valueOf(currencySplit[1]);
            BigDecimal currencyRateToEur = BigDecimal.valueOf(d);

            CurrencyData currencyData = new CurrencyData(currencyName, currencyRateToEur);
            listOfCurrencies.add(currencyData);
        }
        return listOfCurrencies;
    }

    static List<Product> readProductsToList(File file) {
        ArrayList<String> listOfLines = FileHandling.readFileToList(file);
        return fillListOfProducts(listOfLines);
    }

    private static List<Product> fillListOfProducts(List<String> listOfLines) {
        List<Product> listOfProducts = new ArrayList<>();
        for (String listOfLine : listOfLines) {
            String[] productSplit = listOfLine.split(";", 3);
            String productName = productSplit[0];
            Double d = Double.valueOf(productSplit[1]);
            BigDecimal productPrice = BigDecimal.valueOf(d);
            String priceCurrency = productSplit[2];

            Product productData = new Product(productName, productPrice, priceCurrency);
            listOfProducts.add(productData);
        }
        return listOfProducts;
    }

    static List<ProductWithEurPrices> calculateEurPrice(List<Product> products, List<CurrencyData> currencies) {
        List<ProductWithEurPrices> productsEurPrices = new ArrayList<>();

        for (Product product : products) {
            String currency = product.getCurrency();
            BigDecimal priceInCurrency = product.getPrice();
            BigDecimal exchangeRate = getExchangeRate(currency, currencies);

            try {
                BigDecimal priceInEur = priceInCurrency.divide(exchangeRate, 16, RoundingMode.HALF_UP);
                productsEurPrices.add(new ProductWithEurPrices(product, priceInEur));
            } catch (NullPointerException e) {
                System.out.println("Brak waluty na liście lub kurs równy 0 dla " + currency);
            }
        }
        return productsEurPrices;
    }

    static BigDecimal getExchangeRate(String currency, List<CurrencyData> currencies) {
        for (CurrencyData currencyData : currencies) {
            if (currencyData.getName().equals(currency)) {
                return currencyData.getExchangeRate();
            }
        }
        return null;
    }

    static BigDecimal calculatePriceOfAllProductsInEur(List<ProductWithEurPrices> products) {

        BigDecimal result = BigDecimal.valueOf(0);
        for (ProductWithEurPrices product : products) {
            result = result.add(product.getEurPrice());
        }
        return result;
    }

    static BigDecimal calculateAverageValueOfProducts(List<ProductWithEurPrices> products) {

        BigDecimal sum = calculatePriceOfAllProductsInEur(products);
        return sum.divide(BigDecimal.valueOf(products.size()), 16, RoundingMode.HALF_UP);
    }

    static ProductWithEurPrices findMostExpensiveProduct(List<ProductWithEurPrices> products) {

        BigDecimal result = new BigDecimal(0);
        BigDecimal price;
        ProductWithEurPrices finalProduct = new ProductWithEurPrices();

        for (ProductWithEurPrices product : products) {
            price = product.getEurPrice();
            if (result.compareTo(price) < 0) {
                result = price;
                finalProduct = product;
            }
        }
        return finalProduct;
    }

    static ProductWithEurPrices findLessExpensiveProduct(List<ProductWithEurPrices> products) {

        BigDecimal result = products.get(0).getEurPrice();
        BigDecimal price;
        ProductWithEurPrices finalProduct = new ProductWithEurPrices();

        for (ProductWithEurPrices product : products) {
            price = product.getEurPrice();
            if (result.compareTo(price) > 0) {
                result = price;
                finalProduct = product;
            }
        }
        return finalProduct;
    }
}
