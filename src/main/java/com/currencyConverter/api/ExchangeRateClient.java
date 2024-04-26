package main.java.com.currencyConverter.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.java.com.currencyConverter.util.ExchangeRatesFilter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ExchangeRateClient {
    public Map<String, Double> fetchRates() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/e63fcffba14a69175ef29c63/latest/USD"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

        return ExchangeRatesFilter.filterRates(rates, "ARS", "BOB", "BRL", "CLP", "COP", "USD");
    }
}

