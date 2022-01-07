package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListAttribute;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.Attribute;
import it.univpm.Model.TweetPost;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;

/**
 * la classe serve per fare le chiamate all'API e crea gli 
 * arrayList 
 * 
 */
public class ServiceRetrieve {
    public ServiceRetrieve() {}

    //JSON ARRAY - JAVA STANDARD
    public JSONArray getArrayJSON(String str, String element) {
        JSONObject dataObject = new JSONObject(String.valueOf(str));
        JSONArray obj = new JSONArray(dataObject.getJSONArray(element));

        return obj;
    }
    
    /**
     * richiama l'API passando i parametri per riferimento
     * @return l'oggetto contenente il JSON DEI Tweet;
     * nel caso di oggetto nullo ritorna una stringa vuota
     * @throws IOException
     * @throws ParseException
     */
    //STRING - API RESPONSE
    public String getJSONResponseFromAPI(String location, String type, String count, String lang) throws IOException, ParseException {
        ArrayListTweetPost response = action(location, type, count, lang);
        if(response.getAllTweets().size() != 0)
            return response.getAll();
        else
            return "";
    }
    //ArrayListTweetPost - API RESPONSE
    public ArrayListTweetPost getJSONResponseFromAPI() throws IOException, ParseException {
        String location = "Milano"; String type = "recent"; String count = "20"; String lang = "it";
        return action(location, type, count, lang);
    }
    /**
     * recupera gli oggetti necessari per l'orientamento dei parametri successivi 
     * e imposta il fusorario italiano nei Tweet
     * @return l'array contenente i Tweet con l'orario avente il fusorario italiano
     * @throws IOException
     * @throws ParseException
     */
    public ArrayListTweetPost action(String location, String type, String count, String lang) throws IOException, ParseException {
        ArrayListTweetPost array_tweet = new ArrayListTweetPost();
        //Ottengo il JSONArray "statuses" dalla chiamata alla API
        JSONArray arrayObject = new APIJsonRetrieve().retrieve(location, type, count, lang);
        if(arrayObject != null) {
            for (int post_count = 0; post_count < arrayObject.length(); post_count++) {
                //Recupero l'elemento corrente
                JSONObject obj_statuses = arrayObject.getJSONObject(post_count);
                //Data del Tweet
                String created_at = (String) obj_statuses.get("created_at");
                //ID Univoco del Tweet
                String post_id = (String) obj_statuses.get("id_str");
                //Recupero gli oggetti necessarri per l'ottenimento dei parametri successivi
                JSONObject obj_metadata = (JSONObject) obj_statuses.get("metadata");
                JSONObject obj_user = (JSONObject) obj_statuses.get("user");
                JSONObject obj_entities = (JSONObject) obj_statuses.get("entities");
                //Lingua del Tweet
                String iso_language_code = (String) obj_metadata.get("iso_language_code");
                //Tipologia del Tweet
                String result_type = (String) obj_metadata.get("result_type");
                //ID Univoco dell'utente
                String user_id = (String) obj_user.get("id_str");
                //Username dell'utente
                String screen_name = (String) obj_user.get("screen_name");
                //Numero di Tweet dell'utente
                String listed_count = String.valueOf(obj_user.get("listed_count"));
                //Posizione geografica dell'utente
                String user_location = (String) obj_user.get("location");

                //Recupero gli array per l'ottenimento dei parametri successivi
                JSONArray array_hashtags = (JSONArray) obj_entities.getJSONArray("hashtags");
                JSONArray array_user_mentions = (JSONArray) obj_entities.getJSONArray("user_mentions");

                String[] str_array_hashtags = new String[array_hashtags.length()];
                String[] str_array_user_mentions = new String[array_user_mentions.length()];

                //Recupero il nome dell'utente menzionato
                for (int i = 0; i < str_array_user_mentions.length; i++) {
                    str_array_user_mentions[i] = (String) array_user_mentions.getJSONObject(i).get("screen_name");
                }
                //Recupero il testo degli hashtag presente nel Tweet
                for (int i = 0; i < str_array_hashtags.length; i++) {
                    str_array_hashtags[i] = (String) array_hashtags.getJSONObject(i).get("text");
                }

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
                //Creo il Tweet in linea con i parametri ottenuti e lo aggiungo alla lista
                array_tweet.addElement(new TweetPost(created_at, post_id, user_id, screen_name, listed_count, iso_language_code, result_type, user_location, str_array_user_mentions, str_array_hashtags));
            }
        }
        return array_tweet;
    }

    public String getAttributesList(){
        ArrayListAttribute attributes = new ArrayListAttribute();

        //Aggiungo all'ArrayListAttribute un oggetto Attribute per ogni campo gestito del Tweet
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

        //Ritorno - JSON Composto, visualizzazione di tutti gli attributi <campo, descrizione>
        return attributes.getAll();
    }
}