/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package UI;

import Business.WeatherService;
import org.json.JSONObject;
import java.util.Scanner;
import org.json.JSONArray;


public class DesktopUI 
{
    private WeatherService weatherService;

    public DesktopUI(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    public void displayWeatherByCity(String cityName, String stateCode, String countryCode)
    {
    // Call the WeatherService method to fetch weather data by city
    String weatherData = weatherService.fetchWeatherDataByCity(cityName, stateCode, countryCode);

    // Display the fetched weather data
    System.out.println("Weather Information for " + cityName + ":");
    System.out.println(weatherData);
}

    public void displayWeatherByCoordinates(double latitude, double longitude)
    {
    // Call the WeatherService method to fetch weather data by coordinates
    String weatherData = weatherService.fetchWeatherDataByCoordinates(latitude, longitude);

    // Display the fetched weather data
    System.out.println("Weather Information for Latitude: " + latitude + ", Longitude: " + longitude + ":");
    System.out.println(weatherData);
}

    // Method to display basic weather information
    public void displayBasicInformation() {
        String basicInfo = weatherService.fetchBasicInfo();
        System.out.println("\nBasic Weather Information:");
        System.out.println(basicInfo);
    }

    // Method to display sunrise and sunset time
    public void displaySunriseSunsetTime() {
        String sunriseSunset = weatherService.fetchSunriseSunset();
        System.out.println("\nSunrise and Sunset Time:");
        System.out.println(sunriseSunset);
    }

    // Method to display feels-like temperature
    public void displayFeelsLike() 
    {
        String feelsLike = weatherService.fetchFeelsLike();
        System.out.println("\nFeels Like Temperature:");
        System.out.println(feelsLike);
    }
    
    public void getForecastData() 
    {
        String forecastData = weatherService.fetchFiveDayForecast();
        System.out.println("Forecast Data:\n" + forecastData);
    }
    
    public void fetchAndDisplayTimestamp() {
        String timestamp = weatherService.fetchTimestamp();
        System.out.println("Timestamp: " + timestamp);
    }

    public void displayPollutionData() {
        weatherService.getAirPollutionData();
        String pollutionData = weatherService.parsePollutionData();

        try {
            // Parse the pollution data from JSON
            JSONObject pollutionJson = new JSONObject(pollutionData);
            JSONArray pollutantsArray = pollutionJson.getJSONArray("list");

            // Print table headers
            System.out.println("\nAir Pollution Data:");
            System.out.println("--------------------------------------------------------------");
            System.out.printf("| %-15s | %-5s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s |\n",
                    "Pollutant", "AQI", "CO", "NO", "NO2", "O3", "SO2", "PM2.5", "PM10", "NH3");
            System.out.println("--------------------------------------------------------------");

            // Iterate over each pollutant and print its data
            for (int i = 0; i < pollutantsArray.length(); i++) {
                JSONObject pollutant = pollutantsArray.getJSONObject(i);
                JSONObject main = pollutant.getJSONObject("main");
                JSONObject components = pollutant.getJSONObject("components");
                int aqi = main.getInt("aqi");
                double co = components.getDouble("co");
                double no = components.getDouble("no");
                double no2 = components.getDouble("no2");
                double o3 = components.getDouble("o3");
                double so2 = components.getDouble("so2");
                double pm25 = components.getDouble("pm2_5");
                double pm10 = components.getDouble("pm10");
                double nh3 = components.getDouble("nh3");

                // Print pollutant data in table format
                System.out.printf("| %-15s | %-5d | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f |\n",
                        "Pollutant", aqi, co, no, no2, o3, so2, pm25, pm10, nh3);
            }

            // Print table footer
            System.out.println("--------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error parsing pollution data.");
        }
    }
     
    public void displayAirQualityInformation() 
      {
        weatherService.generateAirQualityNotification();
        int aqi = weatherService.getAirQualityIndex();
        String airQualityStatus = weatherService.getAirQualityStatus(aqi);
        System.out.println("Air Quality Status: " + airQualityStatus);
    }
    public void displayBadWeather()
    {
        weatherService.getTempAndHum();
    }
      
   public static void main(String[] args) 
   {
        // Initialize the WeatherService
        WeatherService weatherService = new WeatherService();

        // Create an instance of the UI
        DesktopUI desktopUI = new DesktopUI(weatherService);

        // Prompt user to select the option
        System.out.println("Select an option:");
        System.out.println("1. Check weather by city name");
        System.out.println("2. Check weather by latitude and longitude");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Check weather based on user's choice
        if (choice == 1) {
            // Fetch weather by city name
          System.out.println("Enter city name:");
          String cityName = scanner2.nextLine();
// Prompt user to enter state code
          System.out.println("Enter state code:");
          String stateCode = scanner2.nextLine();

// Prompt user to enter country code
          System.out.println("Enter country code:");
          String countryCode = scanner2.nextLine();
            desktopUI.displayWeatherByCity(cityName, stateCode, countryCode);
        } else if (choice == 2) {
            // Fetch weather by latitude and longitude
            System.out.println("Enter latitude:");
            double latitude = scanner.nextDouble();
            System.out.println("Enter longitude:");
            double longitude = scanner.nextDouble();
            desktopUI.displayWeatherByCoordinates(latitude, longitude);
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
        }
        //desktopUI.displaySunriseSunsetTime();
        desktopUI.displayFeelsLike();
        desktopUI.displayBasicInformation();
        desktopUI.displaySunriseSunsetTime();
        desktopUI.getForecastData();
        desktopUI.fetchAndDisplayTimestamp();
        desktopUI.displayPollutionData();
        desktopUI.displayAirQualityInformation();
        desktopUI.displayBadWeather();
        // Close scanner
        scanner.close();
    }
}
