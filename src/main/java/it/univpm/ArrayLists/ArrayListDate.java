package it.univpm.ArrayLists;

import it.univpm.Model.TweetDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.*;

//ARRAY LIST PER LA STATISTICA
public class ArrayListDate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListTweetPost.class);
    private ArrayList<TweetDate> tweet;
    
    public ArrayListDate() {
        this.tweet = new ArrayList<>();
    }

    public void addElement(TweetDate td) {
        tweet.add(td);
    }

    public TweetDate getElementByID(int index) {
        return tweet.get(index);
    }

    public ArrayList<TweetDate> getAllDate() {
        return this.tweet;
    }

    public String getAll() {
        String str = "{\"tweets\":[";
        //for(TweetPost tp:this.tweet) {
        for (int i = 0; i < this.tweet.size(); i++) {
            str += "{\"post_date\":\"" + this.tweet.get(i).getPostDate() + "\"}";
           
        LOGGER.info("*** REQUEST STATUS [\"statistics\"] - " + HttpStatus.OK + " ***");

        }
		return str;
    }
	
}
