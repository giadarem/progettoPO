package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Statistics.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceStatistics {
    private ArrayListTweetPost tweets;

    public ServiceStatistics() throws IOException, ParseException { tweets = new ServiceRetrieve().getJSONResponseFromAPI(); }
    public ServiceStatistics(ArrayListTweetPost list) throws IOException, ParseException { tweets = list; }

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

    public float freqStatistic(){ return new FreqStatistic().getValues(tweets); }
    public float sumStatistics() throws IOException, ParseException { return new SumStatistic().getValues(tweets); }
    public float minStatistics() throws IOException, ParseException { return new MinStatistic().getValues(tweets); }
    public float maxStatistics() throws IOException, ParseException { return new MaxStatistic().getValues(tweets); }
    public float avgStatistics() throws IOException, ParseException { return new AvgStatistic().getValues(tweets); }
    public float devStatistics() throws IOException, ParseException { return new DevStatistic().getValues(tweets); }

    public ArrayList<Integer> StringToInt(ArrayListTweetPost list){
        ArrayList<Integer> conv = new ArrayList<Integer>();
        for(int i = 0; i < list.getAllTweets().size(); i++) { conv.add(Integer.parseInt(list.getElementByID(i).getUser_post_num())); }
        return conv;
    }
}
