package main.java.com.currencyConverter.util;

import java.util.Map;

public class CurrencyConverter {
    private Map<String, Double> rates;

    public CurrencyConverter(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (rates.containsKey(fromCurrency) && rates.containsKey(toCurrency)) {
            double fromRate = rates.get(fromCurrency);
            double toRate = rates.get(toCurrency);
            return (amount / fromRate) * toRate;
        } else {
            throw new IllegalArgumentException("Invalid currency codes provided.");
        }
    }
}
