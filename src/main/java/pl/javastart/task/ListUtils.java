package pl.javastart.task;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    private static Boolean calculatedPricesInEur = false;

    public static List<CurrencyData> readCurrenciesToList(File file) {
        ArrayList<String> listOfLines = FileHandling.readFileToList(file);
        return fillListOfCurrencies(listOfLines);
    }

    private static List<CurrencyData> fillListOfCurrencies(ArrayList<String> listOfLines) {

        ArrayList<CurrencyData> listOfCurrencies = new ArrayList<>();
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

    private static List<Product> fillListOfProducts(ArrayList<String> listOfLines) {
        ArrayList<Product> listOfProducts = new ArrayList<>();
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

    static void calculateEurPrice(ArrayList<Product> products, ArrayList<CurrencyData> currencies) {
        for (Product product : products) {
            String currency = product.getCurrency();
            BigDecimal priceInCurrency = product.getPrice();
            BigDecimal exchangeRate = getExchangeRate(currency, currencies);
            try {
                BigDecimal priceInEur = priceInCurrency.divide(exchangeRate, 16, RoundingMode.HALF_UP);
                product.setPriceInEur(priceInEur);
            } catch (NullPointerException e) {
                System.out.println("Brak waluty na liście lub kurs równy 0 dla " + currency);
            }
        }
        calculatedPricesInEur = true;
    }

    static BigDecimal getExchangeRate(String currency, ArrayList<CurrencyData> currencies) {
        for (CurrencyData currencyData : currencies) {
            if (currencyData.getName().equals(currency)) {
                return currencyData.getExchangeRate();
            }
        }
        return null;
    }

    static BigDecimal calculatePriceOfAllProductsInEur(ArrayList<Product> products, ArrayList<CurrencyData> currencies) {

        if (calculatedPricesInEur = false) {
            calculateEurPrice(products, currencies);
        }

        BigDecimal result = BigDecimal.valueOf(0);
        for (Product product : products) {
            result = result.add(product.getPriceInEur());
        }
        return result;
    }

    static BigDecimal printAverageValueOfProducts(ArrayList<Product> products, ArrayList<CurrencyData> currencies) {

        if (calculatedPricesInEur = false) {
            calculateEurPrice(products, currencies);
        }

        BigDecimal sum = calculatePriceOfAllProductsInEur(products, currencies);
        return sum.divide(BigDecimal.valueOf(products.size()), 16, RoundingMode.HALF_UP);
    }

    static Product findMostExpensiveProduct(ArrayList<Product> products, ArrayList<CurrencyData> currencies) {

        if (calculatedPricesInEur = false) {
            calculateEurPrice(products, currencies);
        }

        BigDecimal result = new BigDecimal(0);
        BigDecimal price;
        Product finalProduct = new Product();
        for (Product product : products) {
            price = product.getPriceInEur();
            if (result.compareTo(price) == -1) {
                result = price;
                finalProduct = product;
            }
        }
        return finalProduct;
    }

    static Product findLessExpensiveProduct(ArrayList<Product> products, ArrayList<CurrencyData> currencies) {

        if (calculatedPricesInEur = false) {
            calculateEurPrice(products, currencies);
        }

        BigDecimal result = products.get(0).getPriceInEur();
        BigDecimal price;
        Product finalProduct = new Product();
        for (Product product : products) {
            price = product.getPriceInEur();
            if (result.compareTo(price) == 1) {
                result = price;
                finalProduct = product;
            }
        }
        return finalProduct;
    }
}
