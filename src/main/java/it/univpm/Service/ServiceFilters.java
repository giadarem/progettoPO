package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Filter.IncludedFilter;
import it.univpm.Filter.LowerFilter;
import it.univpm.Filter.SearchFilter;
import it.univpm.Filter.UpperFilter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ServiceFilters {
    private ArrayListTweetPost tweets;

    public ServiceFilters() throws IOException, ParseException {
        tweets = new ServiceRetrieve().getJSONResponseFromAPI();
    }

    public ArrayListTweetPost filters(String filter, String field, String value){
        ArrayListTweetPost response;
        switch(filter)
        {
            case "search":
                SearchFilter sf = new SearchFilter(field, value);
                response = sf.getFound(tweets);
                break;
            case "lower":
                LowerFilter lf = new LowerFilter(field, value);
                response = lf.getFound(tweets);
                break;
            case "upper":
                UpperFilter uf = new UpperFilter(field, value);
                response = uf.getFound(tweets);
                break;
            case "included":
                IncludedFilter inf = new IncludedFilter(field, value);
                response = inf.getFound(tweets);
                break;
            default:
                response = null;
        }
        return response;
    }
}
