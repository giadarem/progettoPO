package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListAttribute;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.Attribute;
import it.univpm.Model.TweetPost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class ServiceRetrieve {
    public ServiceRetrieve() {}

    //JSON OBJECT - SIMPLE
    public JSONObject getObjJSONSimple(String str) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(str);

        return obj;
    }

    //JSON OBJECT - JAVA STANDARD
    public org.json.JSONObject getObjJSON(String str) {
        org.json.JSONObject dataObject = new org.json.JSONObject(String.valueOf(str));

        return dataObject;
    }

    //JSON OBJECT - JAVA STANDARD
    public org.json.JSONObject getObjJSON(String str, String element) throws ParseException {
        org.json.JSONObject dataObject = new org.json.JSONObject(String.valueOf(str));

        return dataObject.getJSONObject(element);
    }

    //JSON ARRAY - SIMPLE
    public JSONArray getArrayJSON(String str) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(str);

        return obj;
    }

    //JSON ARRAY - JAVA STANDARD
    public org.json.JSONArray getArrayJSON(org.json.JSONObject obj, String element) {
        org.json.JSONArray dataObj = new org.json.JSONArray(obj.getJSONArray(element));

        return dataObj;
    }

    //JSON ARRAY - JAVA STANDARD
    public org.json.JSONArray getArrayJSON(String str, String element) throws ParseException {
        org.json.JSONObject dataObject = new org.json.JSONObject(String.valueOf(str));
        org.json.JSONArray obj = new org.json.JSONArray(dataObject.getJSONArray(element));

        return obj;
    }

    //STRING - API RESPONSE
    public String getJSONResponseFromAPI(String location, String type, String count, String lang) throws IOException, ParseException {
        return action(location, type, count, lang).getAll();
    }
    //ArrayListTweetPost - API RESPONSE
    public ArrayListTweetPost getJSONResponseFromAPI() throws IOException, ParseException {
        String location = "Milano"; String type = "recent"; String count = "20"; String lang = "it";
        return action(location, type, count, lang);
    }

    public ArrayListTweetPost action(String location, String type, String count, String lang) throws IOException, ParseException {
        ArrayListTweetPost array_tweet = new ArrayListTweetPost();
        org.json.JSONArray arrayObject = new APIJsonRetrieve().retrieve(location, type, count, lang);

        for (int post_count = 0; post_count < arrayObject.length(); post_count++) {
            //Recupero gli elementi dell'oggetto
            org.json.JSONObject obj_statuses = arrayObject.getJSONObject(post_count);
            String created_at = (String) obj_statuses.get("created_at");
            String post_id = (String) obj_statuses.get("id_str");

            org.json.JSONObject obj_metadata = (org.json.JSONObject) obj_statuses.get("metadata");
            org.json.JSONObject obj_user = (org.json.JSONObject) obj_statuses.get("user");
            org.json.JSONObject obj_entities = (org.json.JSONObject) obj_statuses.get("entities");

            String iso_language_code = (String) obj_metadata.get("iso_language_code");
            String result_type = (String) obj_metadata.get("result_type");
            String user_id = (String) obj_user.get("id_str");
            String screen_name = (String) obj_user.get("screen_name");
            String listed_count = String.valueOf(obj_user.get("listed_count"));
            String user_location = (String) obj_user.get("location");

            org.json.JSONArray array_hashtags = (org.json.JSONArray) obj_entities.getJSONArray("hashtags");
            org.json.JSONArray array_user_mentions = (org.json.JSONArray) obj_entities.getJSONArray("user_mentions");

            String[] str_array_hashtags = new String[array_hashtags.length()];
            String[] str_array_user_mentions = new String[array_user_mentions.length()];

            for (int i = 0; i < str_array_user_mentions.length; i++) { str_array_user_mentions[i] = (String) array_user_mentions.getJSONObject(i).get("screen_name"); }
            for (int i = 0; i < str_array_hashtags.length; i++) { str_array_hashtags[i] = (String) array_hashtags.getJSONObject(i).get("text"); }

            //Dichiarazioni variabili cambio data
            String[] date_split = new String[6];
            String[] time_split = new String[3];
            int hours = 0;

            //Conversione data FU GMT +0000 --> GMT +0100
            date_split = created_at.split(" ");
            time_split = date_split[3].split(":");
            hours = Integer.parseInt(time_split[0]);
            hours += 1;
            date_split[3] = hours + ":" + time_split[1] + ":" + time_split[2];
            created_at = "";

            //Ciclo le posizioni della data scomposta + ricomposizione stringa
            for (int date_count = 0; date_count < date_split.length; date_count++) {
                if (date_count == (date_split.length - 1)) {
                    created_at += date_split[date_count];
                } else if (date_count == 4) {
                    date_split[date_count] = "+0100 ";
                    created_at += date_split[date_count];
                } else {
                    created_at += date_split[date_count] + " ";
                }
            }
            array_tweet.addElement(new TweetPost(created_at,post_id,user_id,screen_name,listed_count,iso_language_code,result_type,user_location,str_array_user_mentions,str_array_hashtags));
        }
        return array_tweet;
    }

    public String getAttributesList(){
        ArrayListAttribute attributes = new ArrayListAttribute();

        attributes.addElement(new Attribute("[statuses] created_at", "Type: String - Data di creazione del Tweet"));
        attributes.addElement(new Attribute("[statuses] id_str", "Type: String - ID univoco del Tweet"));
        attributes.addElement(new Attribute("[statuses][metadata] iso_language_code", "Type: String - Lingua del Tweet, filtrabile tra {it, en, es, fr}"));
        attributes.addElement(new Attribute("[statuses][metadata] result_type", "Type: String - Tipologia di Tweet, filtrabile tra {recent, popular, mixed}"));
        attributes.addElement(new Attribute("[statuses][user] id_str", "Type: String - ID univoco dell'utente"));
        attributes.addElement(new Attribute("[statuses][user] screen_name", "Type: String - Username dell'utente"));
        attributes.addElement(new Attribute("[statuses][user] listed_count", "Type: Integer - Numero di post dell'utente"));
        attributes.addElement(new Attribute("[statuses][user] location", "Type: String - Poszione geografica dell'utente"));
        attributes.addElement(new Attribute("[statuses][entities][hashtags] text", "Type: String[] - Hashtag testuali presenti nel Tweet"));
        attributes.addElement(new Attribute("[statuses][entities][user_mentions] screen_name", "Type: String[] - Menzioni utenti testuali presenti nel Tweet"));

        return attributes.getAll();
    }
}