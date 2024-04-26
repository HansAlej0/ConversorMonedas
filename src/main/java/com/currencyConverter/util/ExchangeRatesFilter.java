package main.java.com.currencyConverter.util;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRatesFilter {

    public static Map<String, Double> filterRates(JsonObject rates, String... codes) {
        Map<String, Double> filteredRates = new HashMap<>();

        for (String code : codes) {
            if (rates.has(code)) {
                filteredRates.put(code, rates.get(code).getAsDouble());
            }
        }

        return filteredRates;
    }
}

