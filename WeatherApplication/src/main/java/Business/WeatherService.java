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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;





public class WeatherService 
{
    private static final String API_KEY = "----";
    private static final String GEOCODE_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=" + API_KEY;
    private static final String FORECAST_API_URL = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=" + API_KEY;
    private static final String POLLUTION_API_URL = "https://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=" + API_KEY;
    
    private HttpResponse<String> pollutionResponse;
    
    // Thresholds for poor air quality based on AQI
    private static final int AQI_THRESHOLD_MODERATE = 50;
    private static final int AQI_THRESHOLD_POOR = 100;
    private static final int AQI_THRESHOLD_VERY_POOR = 200;
    private static final int AQI_THRESHOLD_SEVERE = 300;
    private static final double TEMPERATURE_THRESHOLD_LOW = 10; // Low temperature threshold in Celsius
    private static final double HUMIDITY_THRESHOLD_HIGH = 80; // High humidity threshold in percentage

    
    private String weatherData; // Store weather data fetched by fetchWeatherDataByCoordinates
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    

    public String fetchFiveDayForecast() 
    {
        try {
            String urlStr = String.format(FORECAST_API_URL, latitude, longitude);
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

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray forecastList = jsonResponse.getJSONArray("list");

            StringBuilder forecastData = new StringBuilder();

            for (int i = 0; i < forecastList.length(); i++) {
                JSONObject forecastItem = forecastList.getJSONObject(i);
                String dateTime = forecastItem.getString("dt_txt");
                JSONObject main = forecastItem.getJSONObject("main");
                double temperature = main.getDouble("temp");
                JSONArray weatherArray = forecastItem.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String weatherDescription = weather.getString("description");

                // Append the forecast data to the StringBuilder
                forecastData.append("Date/Time: ").append(dateTime).append(", Temperature: ").append(temperature).append("K, Weather: ").append(weatherDescription).append("\n");
            }

            return forecastData.toString();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Failed to fetch forecast data.";
        }
    }

    public String fetchWeatherDataByCoordinates(double latitude, double longitude)
    {
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
        updateTimestamp();
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

        return "Feels like: " + feelsLike + "°K";
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
   
    private void updateTimestamp() {
        timestamp = LocalDateTime.now();
    }

    public String fetchTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Timestamp: " + timestamp.format(formatter);
    }
 
    public void getAirPollutionData()
    {
        String url = String.format(POLLUTION_API_URL, latitude, longitude);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            pollutionResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String parsePollutionData() 
    {
        if (pollutionResponse != null)
        {
            return pollutionResponse.body();
        } else {
            return "Pollution data not available.";
        }
    }

    private String parseWeatherData(String data) 
    {
        try {
            JSONObject json = new JSONObject(data);
            JSONObject main = json.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double minTemp = main.getDouble("temp_min");
            double maxTemp = main.getDouble("temp_max");
            int humidity = main.getInt("humidity");
            double windSpeed = json.getJSONObject("wind").getDouble("speed");

            return String.format("Temperature: %.2f K, Min Temperature: %.2f K, Max Temperature: %.2f K, Humidity: %d%%, Wind Speed: %.2f m/s",
                    temperature, minTemp, maxTemp, humidity, windSpeed);
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing weather data.";
        }
    }
    
    public String generateAirQualityNotification()
    {
        getAirPollutionData();
        JSONObject pollutionData = new JSONObject(pollutionResponse.body());
        String notify="0";
        int aqi = pollutionData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("aqi");
        String airQualityStatus = getAirQualityStatus(aqi);

        if (airQualityStatus.equals("Poor") || airQualityStatus.equals("Very Poor") || airQualityStatus.equals("Severe"))
        {
            notify=("1");
            System.out.println ("Air Quality Alert, Air Quality is " + airQualityStatus + ". Please take necessary precautions.");
        }
        return notify;
    }

    // Method to define air quality status based on AQI
    public String getAirQualityStatus(int aqi) 
    {
        if (aqi <= AQI_THRESHOLD_MODERATE) {
            return "Good";
        } else if (aqi <= AQI_THRESHOLD_POOR) {
            return "Moderate";
        } else if (aqi <= AQI_THRESHOLD_VERY_POOR) {
            return "Poor";
        } else if (aqi <= AQI_THRESHOLD_SEVERE) {
            return "Very Poor";
        } else {
            return "Severe";
        }
    }

    // Method to generate notifications
    public void generateNotification(String title, String message) {
        // This method is a placeholder for actual notification implementation.
        // You can implement notifications for various platforms (desktop, mobile, etc.)
        System.out.println("Notification: " + title + " - " + message);
    }

    public int getAirQualityIndex() 
    {
    // Fetch air pollution data
    getAirPollutionData();

    // Parse the pollution data to get the AQI
    JSONObject pollutionData = new JSONObject(pollutionResponse.body());
    int aqi = pollutionData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("aqi");

    // Return the AQI
    return aqi;
}

   public String getTempAndHum() 
   {
       String status="0";
        try {
            // Parse JSON response to extract weather information
            JSONObject json = new JSONObject(weatherData);
            JSONObject main = json.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double humidity = main.getDouble("humidity");

            // Compare weather conditions with thresholds
            if (temperature <= TEMPERATURE_THRESHOLD_LOW || humidity >= HUMIDITY_THRESHOLD_HIGH) {
                status="1";
                generateNotification("Poor Weather Alert", "Weather conditions are poor. Please take necessary precautions.");
            }
            else
            {
                generateNotification("Weather Is normal", "Chill out and Enjoy!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Method to generate notifications
    private void notifyBadWeather(String title, String message) 
    {
        // This method is a placeholder for actual notification implementation.
        // You can implement notifications for various platforms (desktop, mobile, etc.)
        System.out.println("Notification: " + title + " - " + message);
    }

}
