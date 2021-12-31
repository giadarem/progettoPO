package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Filter.SearchFilter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ServiceFilter {
    private ArrayListTweetPost tweets;

    public ServiceFilter() throws IOException, ParseException {
        tweets = new ServiceRetrieve().getJSONResponseFromAPI();
    }

    public String filters(String filter, String field, String value){
        switch(filter)
        {
            case "search":
                SearchFilter sf = new SearchFilter(field, value);
                return sf.getFound(tweets).getAll();
            default:
                return "";
        }
    }
}