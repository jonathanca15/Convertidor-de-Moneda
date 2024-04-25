import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static final String API_KEY = "92ea327a9e22387c22e81b82";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String BASE_CURRENCY = "MXN"; // Moneda base fija en Peso Mexicano

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cantidad a convertir:");
        double amount = scanner.nextDouble();

        System.out.println("Ingrese la moneda a la que desea convertir (por ejemplo, USD, EUR, GBP, JPY):");
        String targetCurrency = scanner.next().toUpperCase(); 

        double convertedAmount = convertCurrency(amount, targetCurrency);
        if (convertedAmount != -1) {
            System.out.println(amount + " " + BASE_CURRENCY + " = " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Error al convertir la moneda. Inténtelo de nuevo más tarde.");
        }

        scanner.close();
    }

    public static double convertCurrency(double amount, String targetCurrency) {
        try {
            String apiUrl = API_BASE_URL + API_KEY + "/latest/" + BASE_CURRENCY;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = responseReader.readLine()) != null) {
                response.append(line);
            }

            responseReader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (jsonResponse.getString("result").equals("success")) {
                JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                double targetRate = rates.getDouble(targetCurrency);

                return amount * targetRate;
            } else {
                System.out.println("Error: " + jsonResponse.getString("error"));
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
