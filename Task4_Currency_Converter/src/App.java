import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class App {

    private static final String API_KEY = "3789d5fa491338be12531b43";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcomeMessage();
            System.out.print("Enter currency you want to convert from: ");
            String convertFrom = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter currency you want to convert to: ");
            String convertTo = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter Amount of " + convertFrom + " to " + convertTo + " : ");
            double amount = scanner.nextDouble();
            convertCurrency(convertFrom, convertTo, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayWelcomeMessage() {
        System.out.println("********************* Welcome to Currency Converter App *********************");
    }

    public static void convertCurrency(String convertFrom, String convertTo, double amount) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(API_URL + API_KEY + "/latest/" + convertFrom);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject rates = jsonObject.getJSONObject("conversion_rates");
                    calculateConvertedAmount(amount, convertFrom, convertTo, rates);
                }
            } else {
                System.out.println("Error: Failed to retrieve currency data. HTTP Error code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void calculateConvertedAmount(double amount, String convertFrom, String convertTo, JSONObject rates) {
        double fromRate = rates.getDouble(convertFrom);
        double toRate = rates.getDouble(convertTo);
        double convertedAmount = (amount / fromRate) * toRate;
        System.out.println("Converted amount: " + convertedAmount);
    }
}
