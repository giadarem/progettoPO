package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListDate;
import it.univpm.Statistics.TimeStatistic;

import org.json.simple.parser.ParseException;
import java.io.IOException;

public class StatisticService {
	   private ArrayListDate tweets;

	   public StatisticService() throws IOException, ParseException {
	        tweets = new ServiceRetrieve().action();
	    }	       
	   
	   public String statistic(String num){
	        switch(num)
	        {
	            case "30":
	                TimeStatistic ts = new TimeStatistic(num);
	                
	                return ts.getFound(tweets).getAll();
	            default:
	                return "";
	        }
	    }
	}