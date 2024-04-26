package main.java.com.currencyConverter;

import main.java.com.currencyConverter.api.ExchangeRateClient;
import main.java.com.currencyConverter.util.CurrencyConverter;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ExchangeRateClient client = new ExchangeRateClient();
            Map<String, Double> rates = client.fetchRates();
            CurrencyConverter converter = new CurrencyConverter(rates);

            while (true) {
                System.out.println("Currency Converter Menu:");
                System.out.println("1. Convert Currency");
                System.out.println("2. Exit");
                System.out.print("Enter your choice (1-2): ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter the amount to convert: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter the source currency code (e.g., USD): ");
                    String fromCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Enter the target currency code (e.g., EUR): ");
                    String toCurrency = scanner.nextLine().toUpperCase();

                    try {
                        double result = converter.convert(amount, fromCurrency, toCurrency);
                        System.out.printf("%.2f %s is equivalent to %.2f %s%n", amount, fromCurrency, result, toCurrency);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (choice == 2) {
                    System.out.println("Exiting the program.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

