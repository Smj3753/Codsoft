import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {
    private static final String API_URL = "https://api.example.com/exchangerates"; // Replace with the actual API URL

    public static void main(String[] args) {
        // Allow the user to choose base and target currencies (you can use Scanner for user input)
        String baseCurrency = "USD"; // Example: base currency
        String targetCurrency = "EUR"; // Example: target currency

        // Fetch real-time exchange rates from the API
        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

        // Take input from the user for the amount they want to convert
        double amountToConvert = 100.0; // Example: amount to convert

        // Perform currency conversion
        double convertedAmount = amountToConvert * exchangeRate;

        // Display the result
        System.out.println(amountToConvert + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL(API_URL + "?base=" + baseCurrency + "&symbols=" + targetCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response to extract the exchange rate (you'll need a JSON library for this)
            // In a real-world scenario, you should handle exceptions, errors, and edge cases here.

            // For this example, we'll assume a fixed exchange rate of 0.85 (1 USD to 0.85 EUR).
            return 0.85; // Replace with the actual exchange rate from the API response.
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions and errors properly.
        }

        // Return a default exchange rate in case of an error.
        return 1.0;
    }
}
