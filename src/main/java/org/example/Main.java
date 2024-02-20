package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String [] args)
    {
        try {
            URL url = new URL("https://official-joke-api.appspot.com/random_joke");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //Check if connection is made
            int ResponseCode = connection.getResponseCode();

            if(ResponseCode != 200)
                throw new RuntimeException("HttpResponseCode " + ResponseCode);
            else
            {
                StringBuilder informationString = new StringBuilder();

                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext())
                {
                    informationString.append(scanner.nextLine());
                }

                //System.out.println(informationString);

                JSONParser parser = new JSONParser();

                JSONObject dataObject = (JSONObject) parser.parse(String.valueOf(informationString));

                //System.out.println(dataObject);

                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataObject));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
