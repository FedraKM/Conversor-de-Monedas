import com.clasemonedas.Monedas;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PrincipalConAPI {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" ");
            System.out.println("**************************************");
            System.out.println("Sea bienvenido/a al conversor de moneda:");
            System.out.println();
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida: ");
            System.out.println("**************************************");

            int option = -1;
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            if (option == 7) {
                System.out.println("Finalizo el conversor de monedas.");
                break;
            }

            String codigoInicio;
            String codigoDestino;

            switch (option) {
                case 1:
                    codigoInicio = "USD";
                    codigoDestino = "ARS";
                    break;
                case 2:
                    codigoInicio = "ARS";
                    codigoDestino = "USD";
                    break;
                case 3:
                    codigoInicio = "USD";
                    codigoDestino = "BRL";
                    break;
                case 4:
                    codigoInicio = "BRL";
                    codigoDestino = "USD";
                    break;
                case 5:
                    codigoInicio = "USD";
                    codigoDestino = "COP";
                    break;
                case 6:
                    codigoInicio = "COP";
                    codigoDestino = "USD";
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }


            System.out.println();
            System.out.println("Ingresa el valor que deseas convertir: ");
            double montoInteresado = 0.0;

            try {
                montoInteresado = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número válido.");
                scanner.next(); // Clear the invalid input
                continue;
            }


            String direccion = "https://v6.exchangerate-api.com/v6/57660ff55a3e80f9429919c1/pair/"
                    + codigoInicio + "/" + codigoDestino + "/" + montoInteresado;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new Gson();
            Monedas miMonedas = gson.fromJson(json, Monedas.class);


            double result = miMonedas.resultadoConversion;

            System.out.println();
            System.out.printf("El valor de " + montoInteresado + " " + codigoInicio +
                    " corresponde al valor final de =>> " + result + " " + codigoDestino);
        }

        scanner.close();

    }
}
