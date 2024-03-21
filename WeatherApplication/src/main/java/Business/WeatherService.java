/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;


public class WeatherService {
 private static final String API_KEY = "6460ff0675fa856677b402c8b6681643";
  private static final String GEOCODE_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=" + API_KEY;
    
    private String weatherData; // Store weather data fetched by fetchWeatherDataByCoordinates
    private double latitude;
    private double longitude;


public String fetchWeatherDataByCoordinates(double latitude, double longitude) {
    try {
        String urlStr = String.format(API_URL, latitude, longitude);
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        weatherData = response.toString();

        reader.close();
        connection.disconnect();

        // Parse JSON response to extract basic weather information
        JSONObject json = new JSONObject(response.toString());
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double minTemp = main.getDouble("temp_min");
        double maxTemp = main.getDouble("temp_max");
        int humidity = main.getInt("humidity");
        double windSpeed = json.getJSONObject("wind").getDouble("speed");

        // Construct basic weather information string
        String basicInfo = String.format("Temperature: %.2f K, Min Temperature: %.2f K, Max Temperature: %.2f K, Humidity: %d%%, Wind Speed: %.2f m/s",
                temperature, minTemp, maxTemp, humidity, windSpeed);

        return basicInfo;
    } catch (IOException e) {
        e.printStackTrace();
        return "Failed to fetch weather data.";
    }
}


    public String fetchWeatherDataByCity(String cityName, String stateCode, String countryCode) 
    {
        String data = getCoordinatesFromCity(cityName, stateCode, countryCode);
        parseCoordinates(data);
        return fetchWeatherDataByCoordinates(latitude, longitude);
    }

public String fetchSunriseSunset() {
    try {
        // Parse JSON response to extract sunrise and sunset times
        JSONObject json = new JSONObject(weatherData);
        JSONObject sys = json.getJSONObject("sys");

        // Check if the value associated with the "sunset" key is a string
        if (sys.has("sunset") && !sys.isNull("sunset") && sys.get("sunset") instanceof String) {
            String sunset = sys.getString("sunset");
            return "Sunset: " + sunset;
        } else {
            return "Sunset time not available.";
        }
    } catch (JSONException e) {
        e.printStackTrace();
        return "Error fetching sunset time.";
    }
}

    public String fetchFeelsLike() {
        // Parse JSON response to extract feels-like temperature
        JSONObject json = new JSONObject(weatherData);
        JSONObject main = json.getJSONObject("main");
        double feelsLike = main.getDouble("feels_like");

        return "Feels like: " + feelsLike + "°C";
    }

    public String fetchBasicInfo() {
        // Parse JSON response to extract basic weather information
        JSONObject json = new JSONObject(weatherData);
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double humidity = main.getDouble("humidity");
        double windSpeed = json.getJSONObject("wind").getDouble("speed");

        return String.format("Temperature: %.2f°C, Humidity: %.2f%%, Wind Speed: %.2f m/s", temperature, humidity, windSpeed);
    }
    
    private String getCoordinatesFromCity(String cityName, String stateCode, String countryCode) {
        try {
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String urlStr = String.format("%s?q=%s,%s,%s&limit=1&appid=%s", GEOCODE_URL, encodedCityName, stateCode, countryCode, API_KEY);
            URL url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch coordinates.";
        }
    }
    

   private void parseCoordinates(String data) {
    try {
        JSONArray jsonArray = new JSONArray(data);
        if (jsonArray.length() > 0) {
            JSONObject location = jsonArray.getJSONObject(0);
            latitude = location.getDouble("lat");
            longitude = location.getDouble("lon");
        } else {
            // Handle the case when the JSON array is empty
            System.err.println("No coordinates found in the JSON array.");
        }
    } catch (JSONException e) {
        // Handle JSON parsing errors
        e.printStackTrace();
    }
}

}