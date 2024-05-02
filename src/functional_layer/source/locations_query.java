package functional_layer.source;

import functional_layer.Location_Interfaces;
import database_layer.textfile_module.location_save_interface;
import database_layer.textfile_module.location_save_interface.Locations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;

import java.net.URL;
import java.net.URLEncoder;

public class locations_query implements Location_Interfaces {

    public boolean addLocation_Coordinates(String lati, String longi, String db_type) {
        boolean flag = false;
        if (db_type.equals("txt")) {
            // String apiKey = config.API_Key.getAPIKey();
            String geoKey = config.API_Key.getGeoLocAPIKey();
            // get locs and check if lat and lon already exists
            location_save_interface dbl = new database_layer.textfile_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = dbl.getLocations();

            try {
                URL url = new URL(
                        "https://api.geoapify.com/v1/geocode/reverse?lat=" + lati + "&lon=" + longi + "&apiKey="
                                + geoKey);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Accept", "application/json");

                int responseCode = http.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response body
                    BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print the result in string
                    // System.out.println(response.toString());
                    // System.exit(0);

                    // extract country, country code, city, latitude and longitude from the response
                    String country_code = response.toString().split("country_code\":\"")[1].split("\",\"")[0];
                    String country = response.toString().split("country\":\"")[1].split("\",\"")[0];
                    String city = response.toString().split("city\":\"")[1].split("\",\"")[0];
                    String latitude = response.toString().split("lat\":")[1].split(",\"")[0];
                    String longitude = response.toString().split("lon\":")[1].split(",\"")[0];

                    // check for duplicates
                    String new_lat = latitude;
                    String new_lon = longitude;
                    for (location_save_interface.Locations loc : locations) {
                        if (loc.latitude.equals(new_lat) && loc.longitude.equals(new_lon)) {
                            // print error
                            System.out.println("Latitude and Longitude already exist.");
                            return false;
                        }
                    }
                    String current_Date = java.time.LocalDate.now().toString();
                    String current_Time = java.time.LocalTime.now().toString();
                    String date = current_Date.split("-")[2];
                    String month = current_Date.split("-")[1];
                    String year = current_Date.split("-")[0];
                    String hour = current_Time.split(":")[0];
                    String minutes = current_Time.split(":")[1];
                    // add the location to the Locations.txt file in next line
                    location_save_interface db_text_layer = new database_layer.textfile_module.source.location_save();
                    flag = db_text_layer.saveLocation_Names(city, country,
                            country_code, latitude, longitude, date, month, year, hour, minutes);

                } else {
                    // Internet connection error
                    flag = false;
                }
                http.disconnect();
            } catch (IOException e) {
                flag = false;
            }
        } else if (db_type.equals("sql")) {
            // String apiKey = config.API_Key.getAPIKey();
            String geoKey = config.API_Key.getGeoLocAPIKey();
            // get locs and check if lat and lon already exists
            database_layer.sql_module.location_save_interface dbl = new database_layer.sql_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = dbl.getLocations();

            try {
                URL url = new URL(
                        "https://api.geoapify.com/v1/geocode/reverse?lat=" + lati + "&lon=" + longi + "&apiKey="
                                + geoKey);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Accept", "application/json");

                int responseCode = http.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response body
                    BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print the result in string
                    // System.out.println(response.toString());
                    // System.exit(0);

                    // extract country, country code, city, latitude and longitude from the response
                    String country_code = response.toString().split("country_code\":\"")[1].split("\",\"")[0];
                    String country = response.toString().split("country\":\"")[1].split("\",\"")[0];
                    String city = response.toString().split("city\":\"")[1].split("\",\"")[0];
                    String latitude = response.toString().split("lat\":")[1].split(",\"")[0];
                    String longitude = response.toString().split("lon\":")[1].split(",\"")[0];

                    // check for duplicates
                    String new_lat = latitude;
                    String new_lon = longitude;
                    for (location_save_interface.Locations loc : locations) {
                        if (loc.latitude.equals(new_lat) && loc.longitude.equals(new_lon)) {
                            // print error
                            System.out.println("Latitude and Longitude already exist.");
                            return false;
                        }
                    }
                    String current_Date = java.time.LocalDate.now().toString();
                    String current_Time = java.time.LocalTime.now().toString();
                    String date = current_Date.split("-")[2];
                    String month = current_Date.split("-")[1];
                    String year = current_Date.split("-")[0];
                    String hour = current_Time.split(":")[0];
                    String minutes = current_Time.split(":")[1];
                    // add the location to the Locations.txt file in next line
                    database_layer.sql_module.location_save_interface db_sql_layer = new database_layer.sql_module.source.location_save();
                    flag = db_sql_layer.saveLocation_Names(city, country,
                            country_code, latitude, longitude, date, month, year, hour, minutes);

                } else {
                    // Internet connection error
                    flag = false;
                }
                http.disconnect();
            } catch (IOException e) {
                flag = false;
            }
        } else {
            // System.out.println("Invalid database type.");
            return false;
        }

        return flag;
    }

    public boolean addLocation_Names(String city, String country, String db_type) {
        if (db_type.equals("txt")) {
            // String apiKey = config.API_Key.getAPIKey();
            String geoKey = config.API_Key.getGeoLocAPIKey();
            String encodedQuery = null;
            // get all locs and check if it already exists
            location_save_interface dbl = new database_layer.textfile_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = dbl.getLocations();
            boolean flag1 = true;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).city.equals(city) && locations.get(i).country.equals(country)) {
                    // print error
                    System.out.println("Location (City and Country) already exists.");
                    flag1 = false;
                    break;
                }
            }
            if (flag1 == false) {
                return false;
            }
            try {
                encodedQuery = URLEncoder.encode(city + ", " + country, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }

            String apiUrl = "https://api.geoapify.com/v1/geocode/search?text=" + encodedQuery + "&apiKey=" + geoKey;
            URL url = null;
            try {
                url = new URL(apiUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            }

            HttpURLConnection http = null;
            try {
                http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Accept", "application/json");

                int responseCode = http.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print response in string
                    // System.out.println(response.toString());
                    // System.exit(0);

                    String country_code = response.toString().split("country_code\":\"")[1].split("\",\"")[0];
                    String latitude = response.toString().split("lat\":")[1].split(",\"")[0];
                    String longitude = response.toString().split("lon\":")[1].split(",\"")[0];

                    // get time and date
                    String current_Date = java.time.LocalDate.now().toString();
                    String current_Time = java.time.LocalTime.now().toString();

                    String date = current_Date.split("-")[2];
                    String month = current_Date.split("-")[1];
                    String year = current_Date.split("-")[0];
                    String hour = current_Time.split(":")[0];
                    String minutes = current_Time.split(":")[1];

                    // add the location to the Locations.txt file in next line
                    location_save_interface db_text_layer = new database_layer.textfile_module.source.location_save();
                    return db_text_layer.saveLocation_Names(city, country, country_code, latitude, longitude, date,
                            month,
                            year, hour, minutes);
                } else {
                    System.out.println("Error: " + http.getResponseMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
                return false;
            } finally {
                if (http != null) {
                    http.disconnect();
                }
            }
            return true;
        } else if (db_type.equals("sql")) {
            // String apiKey = config.API_Key.getAPIKey();
            String geoKey = config.API_Key.getGeoLocAPIKey();
            String encodedQuery = null;
            // get all locs and check if it already exists
            database_layer.sql_module.location_save_interface dbl = new database_layer.sql_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = dbl.getLocations();
            boolean flag1 = true;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).city.equals(city) && locations.get(i).country.equals(country)) {
                    // print error
                    System.out.println("Location (City and Country) already exists.");
                    flag1 = false;
                    break;
                }
            }
            if (flag1 == false) {
                return false;
            }
            try {
                encodedQuery = URLEncoder.encode(city + ", " + country, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
                return false;
            }

            String apiUrl = "https://api.geoapify.com/v1/geocode/search?text=" + encodedQuery + "&apiKey=" + geoKey;
            URL url = null;
            try {
                url = new URL(apiUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
                return false;
            }

            HttpURLConnection http = null;
            try {
                http = (HttpURLConnection) url.openConnection();
                http.setRequestProperty("Accept", "application/json");

                int responseCode = http.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print response in string
                    // System.out.println(response.toString());
                    // System.exit(0);

                    String country_code = response.toString().split("country_code\":\"")[1].split("\",\"")[0];
                    String latitude = response.toString().split("lat\":")[1].split(",\"")[0];
                    String longitude = response.toString().split("lon\":")[1].split(",\"")[0];

                    // get time and date
                    String current_Date = java.time.LocalDate.now().toString();
                    String current_Time = java.time.LocalTime.now().toString();

                    String date = current_Date.split("-")[2];
                    String month = current_Date.split("-")[1];
                    String year = current_Date.split("-")[0];
                    String hour = current_Time.split(":")[0];
                    String minutes = current_Time.split(":")[1];

                    // add the location to the Locations.txt file in next line
                    database_layer.sql_module.location_save_interface db_sql_layer = new database_layer.sql_module.source.location_save();
                    return db_sql_layer.saveLocation_Names(city, country, country_code, latitude, longitude, date,
                            month,
                            year, hour, minutes);
                } else {
                    System.out.println("Error: " + http.getResponseMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
                return false;
            } finally {
                if (http != null) {
                    http.disconnect();
                }
            }
            return true;
        }
        return false;
    }

    public List<Locations> displayLocs(String db_type) {
        if (db_type.equals("txt")) {
            location_save_interface db_text_layer = new database_layer.textfile_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = db_text_layer.getLocations();

            return locations;
        } else if (db_type.equals("sql")) {
            database_layer.sql_module.location_save_interface db_sql_layer = new database_layer.sql_module.source.location_save();
            java.util.List<location_save_interface.Locations> locations = db_sql_layer.getLocations();

            return locations;
        }
        return null;
    }

    // main method for testing only
    public static void main(String[] args) {
        locations_query lq = new locations_query();
        lq.addLocation_Coordinates("37.5497", "75.3436", "sql");
        // lq.addLocation_Names("Karachi", "Pakistan");
        // lq.displayLocs();
    }
}