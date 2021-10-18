package com.Marvelchallenge.marvelAPI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class MarvelApiApplication {

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(MarvelApiApplication.class, args);
        fetchFromApi();
    }

    public static void fetchFromApi() throws IOException, ParseException {
        URL charactersUrl = new URL("http://gateway.marvel.com/v1/public/characters?orderBy=name&limit=100&ts=1&apikey=cccfd6b5fbfc8bc91c645fd3c39accaa&hash=d609be9bdc98ab7767176a5e3d9f1309");
        HttpURLConnection myHttpURLConnection = (HttpURLConnection) charactersUrl.openConnection();
        myHttpURLConnection.setRequestMethod("GET");
        myHttpURLConnection.connect();

        int responseCode = myHttpURLConnection.getResponseCode();
//        Object myObject = myHttpURLConnection.getContent(); TODO: try this and play
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            String content = "";
            Scanner scanner = new Scanner(charactersUrl.openStream());
            // write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                content = content + scanner.nextLine();
            }
            scanner.close();
            
            // parsing the string into a json object
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            //System.out.println(obj.get("data"));
            JSONObject data_obj = (JSONObject) obj.get("data");
            //System.out.println(data_obj);
            JSONArray results_obj = (JSONArray) data_obj.get("results");
            System.out.println(results_obj);


            //JSONArray myArr = new JSONArray();
//            myArr.put(obj);
            //System.out.println(myArr); // printing response
            //System.out.println(myArr.get(0));
//            JSONObject data_obj = (JSONObject) obj.get("data");
//            JSONObject results_obj = (JSONObject) data_obj.get("results");

            //System.out.println(results_obj);


            // get the required object from the above created object
//            JSONObject results_obj = (JSONObject) data_obj.get("results");
//            System.out.println(results_obj);

            //Get the required data using its key
           // System.out.println(obj.get("TotalRecovered"));

            //Get the required object from the above created object
            //JSONObject obj = (JSONObject) data_obj.get("data");
            //Get the required data using its key
            //System.out.println(obj.get("results"));
        }
    }
}
