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

    public DesktopUI(WeatherService weatherService) 
    {
        this.weatherService = weatherService;
    }
    
    public String displayWeatherByCity(String cityName, String stateCode, String countryCode)
    {
    // Call the WeatherService method to fetch weather data by city
    String weatherData = weatherService.fetchWeatherDataByCity(cityName, stateCode, countryCode);

    // Display the fetched weather data
    //System.out.println("Weather Information for " + cityName + ":");
    //System.out.println(weatherData);
    return weatherData;
}

    public String displayWeatherByCoordinates(double latitude, double longitude)
    {
    // Call the WeatherService method to fetch weather data by coordinates
    String weatherData = weatherService.fetchWeatherDataByCoordinates(latitude, longitude);

    // Display the fetched weather data
   // System.out.println("Weather Information for Latitude: " + latitude + ", Longitude: " + longitude + ":");
    //System.out.println(weatherData);
    return weatherData;
}

    // Method to display basic weather information
    public String displayBasicInformation() {
        String basicInfo = weatherService.fetchBasicInfo();
        System.out.println("\nBasic Weather Information:");
        System.out.println(basicInfo);
        return basicInfo;
    }

    // Method to display sunrise and sunset time
    public String displaySunriseSunsetTime() {
        String sunriseSunset = weatherService.fetchSunriseSunset();
        System.out.println("\nSunrise and Sunset Time:");
        System.out.println(sunriseSunset);
        return sunriseSunset;
    }

    // Method to display feels-like temperature
    public String displayFeelsLike() 
    {
        String feelsLike = weatherService.fetchFeelsLike();
        System.out.println("\nFeels Like Temperature:");
        System.out.println(feelsLike);
        return feelsLike;
    }
    
    public String getForecastData() 
    {
        String forecastData = weatherService.fetchFiveDayForecast();
        System.out.println("Forecast Data:\n" + forecastData);
        return forecastData;
    }
    
    public String fetchAndDisplayTimestamp() {
        String timestamp = weatherService.fetchTimestamp();
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }


   public String displayPollutionDataTwo() {
    weatherService.getAirPollutionData();
    StringBuilder pollutionDataString = new StringBuilder();

    try {
        // Parse the pollution data from JSON
        JSONObject pollutionJson = new JSONObject(weatherService.parsePollutionData());
        JSONArray pollutantsArray = pollutionJson.getJSONArray("list");

        // Append the title
        //pollutionDataString.append("\nAir Pollution Data:\n");

        // Iterate over each pollutant and append its data
        for (int i = 0; i < pollutantsArray.length(); i++) {
            JSONObject pollutant = pollutantsArray.getJSONObject(i);
            JSONObject components = pollutant.getJSONObject("components");
            int aqi = pollutant.getJSONObject("main").getInt("aqi");

            // Append pollutant name and AQI
            pollutionDataString.append("Carbon Monoxide (CO) : AQI ").append(aqi).append("\n");
            pollutionDataString.append("Nitrogen Monoxide (NO) : ").append(components.getDouble("co")).append("\n");
            pollutionDataString.append("Nitrogen Dioxide (NO2) : ").append(components.getDouble("no")).append("\n");
            pollutionDataString.append("Ozone (O3) : ").append(components.getDouble("no2")).append("\n");
            pollutionDataString.append("Sulfur Dioxide (SO2) : ").append(components.getDouble("o3")).append("\n");
            pollutionDataString.append("(PM2.5) : ").append(components.getDouble("so2")).append("\n");
            pollutionDataString.append("(PM10) : ").append(components.getDouble("pm2_5")).append("\n");
            pollutionDataString.append("Ammonia (NH3) : ").append(components.getDouble("pm10")).append("\n");


        }
    } catch (Exception e) {
        e.printStackTrace();
        pollutionDataString.append("Error parsing pollution data.");
    }

    return pollutionDataString.toString();
}


    public String displayAirQualityInformation() 
      {
        
        int aqi = weatherService.getAirQualityIndex();
        String airQualityStatus = weatherService.getAirQualityStatus(aqi);
        System.out.println("Air Quality Status: " + airQualityStatus);
        return airQualityStatus;
    }
    
    public String getAQiMessage()
    {
        String message= weatherService.generateAirQualityNotification();
        return message;
    }
    public String displayBadWeather()
    {
        String status;
        status=weatherService.getTempAndHum();
        return status;
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
        desktopUI.displayPollutionDataTwo();
        desktopUI.displayAirQualityInformation();
        desktopUI.displayBadWeather();
        // Close scanner
        scanner.close();
    }
}
