// Proyecto que convierte entre dólares y pesos mexicanos, argentinos y colombianos
// Utiliza la API de ExchangeRate-API para obtener las tasas de cambio
// Requiere un archivo de propiedades llamado config.properties con la API key

import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Main {
    public static void main(String[] args) {
        int opcion = 0;
        double cambio = 0;

        // Cargar la API key desde el archivo de propiedades
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades: " + e.getMessage());
        }

        // Conexión a la API de ExchangeRate
        String apiKey = properties.getProperty("api_key");
        URI url = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();
        HttpResponse<String> response = null;
        int statusCode = 0;
        ExchangeRate exchangeRateJson = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println("Respuesta cruda de la API: " + response.body());
            statusCode = response.statusCode();
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }

        Gson gson = new Gson();

        if (statusCode == HttpURLConnection.HTTP_OK) {
            try {
                exchangeRateJson = gson.fromJson(response.body(), ExchangeRate.class);
            } catch (JsonSyntaxException e) {
                throw new RuntimeException("Error al convertir la respuesta JSON: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Error al consultar la API: " + response.body());
        }

        // Interacción con el usuario
        Scanner teclado = new Scanner(System.in);
        double cantidad = 0;

        while (opcion != 7) {
            imprimirMenu();
            System.out.println("Seleccione una opción:");
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingrese un número de opción válido");
                teclado.next();
            }

            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("\nIngrese la cantidad a convertir:");
                while (!teclado.hasNextDouble()) {
                    System.out.println("Por favor, ingrese una cantidad válida");
                    teclado.next();
                }
                cantidad = teclado.nextDouble();
            }

            switch (opcion) {
                case 1:
                    cambio = exchangeRateJson.getConversionRates().get("MXN");
                    System.out.printf("\nLa cantidad de %.2f USD en Pesos Mexicanos es: %.2f\n", cantidad,
                            cantidad * cambio);
                    break;
                case 2:
                    cambio = exchangeRateJson.getConversionRates().get("MXN");
                    System.out.printf("\nLa cantidad de %.2f Pesos Mexicanos en USD es: %.2f\n", cantidad,
                            cantidad / cambio);
                    break;
                case 3:
                    cambio = exchangeRateJson.getConversionRates().get("ARS");
                    System.out.printf("\nLa cantidad de %.2f USD en Pesos Argentinos es: %.2f\n", cantidad,
                            cantidad * cambio);
                    break;
                case 4:
                    cambio = exchangeRateJson.getConversionRates().get("ARS");
                    System.out.printf("\nLa cantidad de %.2f Pesos Argentinos en USD es: %.2f\n", cantidad,
                            cantidad / cambio);
                    break;
                case 5:
                    cambio = exchangeRateJson.getConversionRates().get("COP");
                    System.out.printf("\nLa cantidad de %.2f USD en Pesos Colombianos es: %.2f\n", cantidad,
                            cantidad * cambio);
                    break;
                case 6:
                    cambio = exchangeRateJson.getConversionRates().get("COP");
                    System.out.printf("\nLa cantidad de %.2f Pesos Colombianos en USD es: %.2f\n", cantidad,
                            cantidad / cambio);
                    break;
                case 7:
                    System.out.println("\nGracias por utilizar el Conversor de Monedas");
                    break;
                default:
                    System.out.println("\nOpción no válida");
            }
        }

        teclado.close();
    }

    public static void imprimirMenu() {
        System.out.println("""
                \n======================================
                *** Elaborado por: Alfonso Enriquez ***
                ======================================
                \nBienvenido al Conversor de Monedas\n
                1 - Dolar => Peso Mexicano
                2 - Peso Mexicano => Dolar
                3 - Dolar => Peso Argentino
                4 - Peso Argentino => Dolar
                5 - Dolar => Peso Colombiano
                6 - Peso Colombiano => Dolar
                7 - Salir\n
                ======================================
                """);
    }
}