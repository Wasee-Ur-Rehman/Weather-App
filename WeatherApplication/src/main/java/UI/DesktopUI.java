/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package UI;

import Business.WeatherService;
import org.json.JSONObject;
import java.util.Scanner;

public class DesktopUI 
{
    private WeatherService weatherService;

    public DesktopUI(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    public void displayWeatherByCity(String cityName, String stateCode, String countryCode) {
    // Call the WeatherService method to fetch weather data by city
    String weatherData = weatherService.fetchWeatherDataByCity(cityName, stateCode, countryCode);

    // Display the fetched weather data
    System.out.println("Weather Information for " + cityName + ":");
    System.out.println(weatherData);
}

public void displayWeatherByCoordinates(double latitude, double longitude) {
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
    public void displayFeelsLike() {
        String feelsLike = weatherService.fetchFeelsLike();
        System.out.println("\nFeels Like Temperature:");
        System.out.println(feelsLike);
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
        // Close scanner
        scanner.close();
    }
}