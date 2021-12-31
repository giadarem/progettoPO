package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Filter.IncludedFilter;
import it.univpm.Filter.LowerFilter;
import it.univpm.Filter.SearchFilter;
import it.univpm.Filter.UpperFilter;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ServiceFilter {
    private ArrayListTweetPost tweets;

    public ServiceFilter() throws IOException, ParseException {
        tweets = new ServiceRetrieve().getJSONResponseFromAPI();
    }

    public String filters(String filter, String field, String value){
        String response = "";
        switch(filter)
        {
            case "search":
                SearchFilter sf = new SearchFilter(field, value);
                response = sf.getFound(tweets).getAll();
                break;
            case "lower":
                LowerFilter lf = new LowerFilter(field, value);
                response = lf.getFound(tweets).getAll();
                break;
            case "upper":
                UpperFilter uf = new UpperFilter(field, value);
                response = uf.getFound(tweets).getAll();
                break;
            case "included":
                IncludedFilter inf = new IncludedFilter(field, value);
                response = inf.getFound(tweets).getAll();
                break;
            default:
                response = "";
        }
        return response;
    }
}
