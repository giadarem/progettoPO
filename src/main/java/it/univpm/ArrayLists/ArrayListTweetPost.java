package it.univpm.ArrayLists;

import it.univpm.Model.TweetPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.*;

public class ArrayListTweetPost {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListTweetPost.class);
    private ArrayList<TweetPost> tweet;

    public ArrayListTweetPost() {
        this.tweet = new ArrayList<>();
    }

    public void addElement(TweetPost tp) {
        tweet.add(tp);
    }

    public TweetPost getElementByID(int index) {
        return tweet.get(index);
    }

    public ArrayList<TweetPost> getAllTweets() {
        return this.tweet;
    }

    public String getAll() {
        String str = "{\"tweets\":[";
        //for(TweetPost tp:this.tweet) {
        for (int i = 0; i < this.tweet.size(); i++) {
            str += "{\"post_date\":\"" + this.tweet.get(i).getPostDate() + "\",";
            str += "\"post_id\":\"" + this.tweet.get(i).getPost_id() + "\",";
            str += "\"user_id\":\"" + this.tweet.get(i).getUser_id() + "\",";
            str += "\"user_name\":\"" + this.tweet.get(i).getUser_name() + "\",";
            str += "\"user_post_num\":\"" + this.tweet.get(i).getUser_post_num() + "\",";
            str += "\"post_lang\":\"" + this.tweet.get(i).getPost_lang() + "\",";
            str += "\"post_type\":\"" + this.tweet.get(i).getPost_type() + "\",";
            str += "\"user_location\":\"" + this.tweet.get(i).getLocation() + "\"";
            if(this.tweet.get(i).getUser_post_mentions().length != 0)
            {
                str += ",\"user_post_mentions\":[";
                String[] mention = this.tweet.get(i).getUser_post_mentions();
                for(int mention_count = 0; mention_count < mention.length; mention_count++)
                {
                    if ((mention_count+1) == mention.length)
                        str += "{\"screen_name\":\""+mention[mention_count]+"\"}]";
                    else
                        str += "{\"screen_name\":\""+mention[mention_count]+"\"},";
                }
            } else { str += ",\"user_post_mentions\":[]"; }

            if(this.tweet.get(i).getPost_hashtags().length != 0)
            {
                str += ",\"post_hashtags\":[";
                String[] hashtags = this.tweet.get(i).getPost_hashtags();
                for(int hashtags_count = 0; hashtags_count < hashtags.length; hashtags_count++)
                {
                    if ((hashtags_count+1) == hashtags.length)
                        str += "{\"text\":\""+hashtags[hashtags_count]+"\"}";
                    else
                        str += "{\"text\":\""+hashtags[hashtags_count]+"\"},";
                }
                if ((i+1) == this.tweet.size()) str += "]}"; else str += "]},";
            } else { if ((i+1) == this.tweet.size()) str += ",\"post_hashtags\":[]}"; else str += ",\"post_hashtags\":[]},"; }
        }
        str += "]}";
        LOGGER.info("*** REQUEST STATUS - " + HttpStatus.OK + " ***");

        return str;
    }
}