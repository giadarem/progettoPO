package com.example.demo;

import org.json.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
     
        try {
            //Public API:
            //https://www.metaweather.com/api/location/search/?query=<CITY>
            //https://www.metaweather.com/api/location/44418/

            URL url = new URL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/search/tweets.json?geocode=45.4654219,9.1859243,10km&result_type=recent&count=15&lang=it");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                //Converto la Stringa del webservice in JSON Object
                JSONObject dataObject = new JSONObject(String.valueOf(informationString));
                //Recupero L'Array contenente i tweet dal JSON Object
                JSONArray dataArray = new JSONArray(dataObject.getJSONArray("statuses"));
                String created_at_post[] = new String[dataArray.length()];
                //Per ogni tweet...
                for(int post_count=0; post_count < dataArray.length(); post_count++)
                {
                    //Recupero la data di creazione del post
                    String created_at = (String) dataArray.getJSONObject(post_count).get("created_at");
                    System.out.println(post_count + " " + created_at);
                    //Correzione orario con Fuso Orario GMT +1
                    String date_split[] = new String[6];
                    String time_split[] = new String[3];
                    int hours = 0;
                    date_split = created_at.split(" ");
                    time_split = date_split[3].split(":");
                    hours = Integer.parseInt(time_split[0]);hours += 1;
                    date_split[3] = hours+":"+time_split[1]+":"+time_split[2];
                    created_at = "";
                    for(int date_count = 0; date_count<date_split.length; date_count++)
                    {
                        if (date_count == (date_split.length-1))
                            created_at += date_split[date_count];
                        else if (date_count == 4) 
                        {
                            date_split[date_count] = "+0100 ";
                            created_at += date_split[date_count];
                        }
                        else
                            created_at += date_split[date_count] + " ";
                    }
                    created_at_post[post_count] = created_at;
                    System.out.println(post_count + " " + created_at_post[post_count]);
                    //Data corretta con Fuso Oraio GMT +1
                }
                //Statistiche
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}