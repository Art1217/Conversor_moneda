package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyService service = new CurrencyService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("******************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =)");
        System.out.println("******************************************");

        while (running) {
            System.out.println("\n1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Dólar =>> Sol peruano");
            System.out.println("8) Sol peruano =>> Dólar");
            System.out.println("9) Salir");
            System.out.print("Elija una opción válida: ");

            int option = scanner.nextInt();

            if (option == 9) {
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                running = false;
                continue;
            }

            String from = "";
            String to = "";

            switch (option) {
                case 1 -> {
                    from = "USD";
                    to = "ARS";
                }
                case 2 -> {
                    from = "ARS";
                    to = "USD";
                }
                case 3 -> {
                    from = "USD";
                    to = "BRL";
                }
                case 4 -> {
                    from = "BRL";
                    to = "USD";
                }
                case 5 -> {
                    from = "USD";
                    to = "COP";
                }
                case 6 -> {
                    from = "COP";
                    to = "USD";
                }
                case 7 -> {
                    from = "USD";
                    to = "PEN";
                }
                case 8 -> {
                    from = "PEN";
                    to = "USD";
                }
                default -> {
                    System.out.println("Opción no válida.");
                    continue;
                }
            }

            System.out.print("Ingrese el valor que desea convertir: ");
            double amount = scanner.nextDouble();

            try {
                ExchangeRateResponse response = service.getExchangeRates(from);
                Double rate = response.conversion_rates().get(to);

                if (rate != null) {
                    double converted = amount * rate;
                    System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                            amount, from, converted, to);
                } else {
                    System.out.println("No se encontró la tasa de conversión.");
                }
            } catch (Exception e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }
    }
}