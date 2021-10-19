package com.Marvelchallenge.marvelAPI;

import com.Marvelchallenge.marvelAPI.entities.MarvelCharacter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Paths.get;

@SpringBootApplication
public class MarvelApiApplication {

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(MarvelApiApplication.class, args);
        FetchCharactersFromApi();
    }

    public static HttpURLConnection CreateConnection(URL charactersUrl, String requestMethod) throws IOException {
        HttpURLConnection myHttpURLConnection = (HttpURLConnection) charactersUrl.openConnection();
        myHttpURLConnection.setRequestMethod(requestMethod);

        return myHttpURLConnection;
    }

    public static void FetchCharactersFromApi() throws IOException, ParseException {
        URL charactersUrl = new URL(Constants.ApiUrlCharacters);
        HttpURLConnection myHttpURLConnection = CreateConnection(charactersUrl, "GET");
        myHttpURLConnection.connect();

        int responseCode = myHttpURLConnection.getResponseCode();
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

            JSONObject data_obj = (JSONObject) obj.get("data");
            JSONArray results_objArr = (JSONArray) data_obj.get("results");
            final int resultsArrLength = results_objArr.toArray().length;

            List<Long> charactersId = new ArrayList<Long>();
            List<MarvelCharacter> characterList = new ArrayList<MarvelCharacter>();

            for (int i = 0; i < resultsArrLength; i++) {
                System.out.println("for loop with i = " + i + " id is : " + ((JSONObject) results_objArr.get(i)).get("id").getClass().getSimpleName());
                System.out.println("for loop with i = " + i + " name is : " + ((JSONObject) results_objArr.get(i)).get("name"));
                System.out.println("for loop with i = " + i + " description is : " + ((JSONObject) results_objArr.get(i)).get("description"));
                System.out.println("for loop with i = " + i + " thumbnail is : " + ((JSONObject) results_objArr.get(i)).get("thumbnail"));

                System.out.println("what type is id: " + ((JSONObject) results_objArr.get(i)).get("id").getClass().getSimpleName());
                System.out.println("what type is name: " + ((JSONObject) results_objArr.get(i)).get("name").getClass().getSimpleName());
                System.out.println("what type is description: " + ((JSONObject) results_objArr.get(i)).get("description").getClass().getSimpleName());
                System.out.println("what type is thumbnail: " + ((JSONObject) results_objArr.get(i)).get("thumbnail").getClass().getSimpleName());

                // setting constructor arguments
                long id = (long) ((JSONObject) results_objArr.get(i)).get("id");
                String name = (String) ((JSONObject) results_objArr.get(i)).get("name");
                String description = (String) ((JSONObject) results_objArr.get(i)).get("description");
                //JSONObject thumbnail = (JSONObject) ((JSONObject) results_objArr.get(i)).get("thumbnail"); TODO: fix later, doesnt let me run the MarvelCharct constructor with it

                MarvelCharacter currentMarvel = new MarvelCharacter(id, name, description);
                characterList.add(currentMarvel);

                System.out.println("my id stored is: " + id);
                System.out.println("my name stored is: " + name);
                System.out.println("my description stored is: " + description);
                //System.out.println("my thumbnail stored is: " + thumbnail);
                charactersId.add(id);
            }
            System.out.println("characters id : " + charactersId);
            System.out.println("List of Marvel characters : " + characterList);

            // ------------>>>> ((JSONObject) results_objArr.get(0)).get("name")
        }
    }
}
