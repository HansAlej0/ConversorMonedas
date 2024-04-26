package main.java.com.currencyConverter;

import main.java.com.currencyConverter.api.ExchangeRateClient;
import main.java.com.currencyConverter.util.CurrencyConverter;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Pide al usuario ingresar la moneda base
            System.out.println("Bienvenido al Convertidor de Divisas.");
            System.out.print("Introduce el código de la moneda base (por ejemplo, USD): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            // Crea una instancia de ExchangeRateClient y obtiene las tasas para la moneda base ingresada
            ExchangeRateClient client = new ExchangeRateClient();
            Map<String, Double> rates = client.fetchRates(baseCurrency);
            CurrencyConverter converter = new CurrencyConverter(rates);

            while (true) {
                System.out.println("\nMenú del Convertidor de Divisas:");
                System.out.println("1. Convertir Divisas");
                System.out.println("2. Salir");
                System.out.print("Elige una opción (1-2): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume el carácter de nueva línea

                if (choice == 1) {
                    System.out.print("Ingresa el monto a convertir: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume el carácter de nueva línea

                    System.out.print("Ingresa el código de la moneda de origen (por ejemplo, EUR): ");
                    String fromCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Ingresa el código de la moneda destino (por ejemplo, JPY): ");
                    String toCurrency = scanner.nextLine().toUpperCase();

                    try {
                        double result = converter.convert(amount, fromCurrency, toCurrency);
                        System.out.printf("Resultado: %.2f %s equivalen a %.2f %s.%n", amount, fromCurrency, result, toCurrency);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al convertir: " + e.getMessage());
                    }
                } else if (choice == 2) {
                    System.out.println("Saliendo del programa. ¡Gracias por usar nuestro servicio!");
                    break;
                } else {
                    System.out.println("Opción no válida. Por favor, introduce 1 o 2.");
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
