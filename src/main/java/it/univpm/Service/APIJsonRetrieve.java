package it.univpm.Service;

import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIJsonRetrieve {
    public APIJsonRetrieve() {}

    public org.json.JSONArray retrieve(String location, String type, String count, String lang) throws IOException, ParseException, ParseException {
        String geolocation = "";

        //check params
        switch (location) {
            case "Milano":
                geolocation = "45.4654219,9.1859243,10km";
                break;
            case "Napoli":
                geolocation = "40.514680,14.163612,10km";
                break;
            case "Ancona":
                geolocation = "43.6158299,13.518915,10km";
                break;
            default:
                break;
        }
        if (!type.equals("recent") && !type.equals("popular") && !type.equals("mixed")) type = "recent";
        if (Integer.parseInt(count) <= 0) count = String.valueOf(15);
        if (!lang.equals("it") && !lang.equals("en") && !lang.equals("fr") && !lang.equals("es")) lang = "it";

        String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/search/tweets.json?geocode=" + geolocation + "&result_type=" + type + "&count=" + count + "&lang=" + lang;

        return call(url);
    }

    private org.json.JSONArray call(String url) throws ParseException, IOException {
        URL call_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) call_url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int response_code = conn.getResponseCode();

        if (response_code != 200) {
            throw new RuntimeException("httpResponseCode: " + response_code);
        } else {
            //Action - Api Call
            StringBuilder streamStr = new StringBuilder();
            Scanner scanner = new Scanner(call_url.openStream());
            while (scanner.hasNext()) {
                streamStr.append(scanner.nextLine());
            }
            scanner.close();

            //Conversione in JSON OBJ
            JSONArray arrayObject = new ServiceRetrieve().getArrayJSON(String.valueOf(streamStr), "statuses");
            return arrayObject;
        }
    }
}
