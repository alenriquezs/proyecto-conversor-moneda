// Proyecto que convierte entre dólares y pesos mexicanos, argentinos y colombianos
// Utiliza la API de ExchangeRate-API para obtener las tasas de cambio

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/USD");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();
        HttpResponse<String> response = null;

            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Respuesta cruda de la API: " + response.body());
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener las tasas de cambio: " + e.getMessage());
            }

            Gson gson = new Gson();

        // Interacción con el usuario
        Scanner teclado = new Scanner(System.in);
        double cantidad = 0;
        String menu = """
                 \n                 
                 Bienvenido al Conversor de Monedas \n
                 1 - Dolar => Peso Mexicano
                 2 - Peso Mexicano => Dolar
                 3 - Dolar => Peso Argentino
                 4 - Peso Argentino => Dolar
                 5 - Dolar => Peso Colombiano
                 6 - Peso Colombiano => Dolar 
                 7 - Salir
                """;

        while (opcion != 7) {
            System.out.println(menu);
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido para la opción.");
                teclado.next(); // Limpiamos el buffer de entrada
            }
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese la cantidad:");
                while (!teclado.hasNextDouble()) {
                    System.out.println("Por favor, ingrese un número válido para la cantidad.");
                    teclado.next(); // Limpiamos el buffer de entrada
                }
                cantidad = teclado.nextDouble();
            }
        }

        teclado.close();
    }
}