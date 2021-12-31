package it.univpm.Filter;

import java.util.ArrayList;
import it.univpm.Abstract.AbstractFilter;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;
import org.json.JSONException;

public class SearchFilter extends AbstractFilter{
    private String search_value = "";

    public SearchFilter(String search_field, String search_value){
        super(search_field);
        this.setSearch_value(search_value);
    }

    public String getSearch_value() {
        return search_value;
    }

    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    @Override
    public boolean searchElement(TweetPost elem) {
        boolean check = false;
        switch(getFields())
        {
            case "hashtags":
                String[] str_hashtags = elem.getPost_hashtags();
                for(int i = 0; i < str_hashtags.length; i++)
                {
                    if(str_hashtags[i].contains(this.getSearch_value())){ check = true; }
                }
                break;
            case "username":
                if(elem.getUser_name().contains(this.getSearch_value()))
                    check = true;
                break;
            case "mentions":
                String[] str = elem.getUser_post_mentions();
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].contains(this.getSearch_value())){ check = true; }
                }
                break;
        }
        return check;
    }

    @Override
    public ArrayListTweetPost getFound(ArrayListTweetPost list) {
        ArrayListTweetPost array = new ArrayListTweetPost();
        for(int i = 0; i < list.getAllTweets().size(); i++)
        {
            if(searchElement(list.getElementByID(i)))
                array.addElement(list.getElementByID(i));
        }
        return array;
    }
}
