package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Statistics.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

<<<<<<< HEAD
=======
/**
 *  Service dedicato al calcolo delle statistiche.
 */

>>>>>>> 202cfeda42693eceeed05c1e40a98afe2d262ce8
public class ServiceStatistics {
    private ArrayListTweetPost tweets;

    public ServiceStatistics() throws IOException, ParseException {
        //Recupero l'ArrayListTweetPost effettuando una chiamata alla API con parametri standard
        //location = Milano, result_type = recent, count = 20, lang = it
        tweets = new ServiceRetrieve().getJSONResponseFromAPI();
    }
    public ServiceStatistics(ArrayListTweetPost list) { tweets = list; }
    //Composizione JSON in base al campo su cui effettuare le statistiche
    //created_at - Frequenza Tweet
    //listed_count - Somma, Minimo, Media, Massimo, Deviazione
    public String statistics(String stats_field) throws IOException, ParseException {
        String str = "{\"statistics\":{";
        switch(stats_field) {
            case "created_at":
                str += "\"Field\":\""+stats_field+"\",";
                str += "\"Frequency\":\"Every "+String.valueOf(freqStatistic())+" Seconds\"";
                str += "}}";
                break;
            case "listed_count":
                str += "\"Field\":\""+stats_field+"\",";
                str += "\"Sum\":\""+String.valueOf(sumStatistics())+"\",";
                str += "\"Max\":\""+String.valueOf(maxStatistics())+"\",";
                str += "\"Min\":\""+String.valueOf(minStatistics())+"\",";
                str += "\"Avg\":\""+String.valueOf(avgStatistics())+"\",";
                str += "\"Dev\":\""+String.valueOf(devStatistics())+"\"";
                str += "}}";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    //Ritorno valore frequenza Tweet
    public float freqStatistic(){ return new FreqStatistic().getValues(tweets); }
    //Ritorno valore somma post utenti
    public float sumStatistics() throws IOException, ParseException { return new SumStatistic().getValues(tweets); }
    //Ritorno valore minimo post utenti
    public float minStatistics() throws IOException, ParseException { return new MinStatistic().getValues(tweets); }
    //Ritorno valore massimo post utenti
    public float maxStatistics() throws IOException, ParseException { return new MaxStatistic().getValues(tweets); }
    //Ritorno valore media post utenti
    public float avgStatistics() throws IOException, ParseException { return new AvgStatistic().getValues(tweets); }
    //Ritorno valore deviazione standard post utenti
    public float devStatistics() throws IOException, ParseException { return new DevStatistic().getValues(tweets); }

    public ArrayList<Integer> StringToInt(ArrayListTweetPost list){
        ArrayList<Integer> conv = new ArrayList<Integer>();
        for(int i = 0; i < list.getAllTweets().size(); i++) {
            //Conversione numero post utente di ArrayList list in Intero + aggiunta ad ArrayList conv
            conv.add(Integer.parseInt(list.getElementByID(i).getUser_post_num()));
        }
        return conv;
    }
}
