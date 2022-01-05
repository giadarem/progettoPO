package it.univpm.Service;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Filter.IncludedFilter;
import it.univpm.Filter.LowerFilter;
import it.univpm.Filter.SearchFilter;
import it.univpm.Filter.UpperFilter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 *  Service dedicato al calcolo dell'array filtrato
 *  che in base al JSONObject filtro passato dall'utente,   
 *  sceglie ed applica il filtro selezionato.
 */

public class ServiceFilters {
    private ArrayListTweetPost tweets;

    public ServiceFilters() throws IOException, ParseException {
        //Recupero l'ArrayListTweetPost effettuando una chiamata alla API con parametri standard
        //location = Milano, result_type = recent, count = 20, lang = it
        tweets = new ServiceRetrieve().getJSONResponseFromAPI();
    }

    public ArrayListTweetPost filters(String filter, String field, String value){
        ArrayListTweetPost response;
        //Search - hashtags, mentions, username
        //Lower, Upper, Included - post_num
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
        //Ritorno - ArrayListTweetPost con i soli Tweet che soddisfano i filtri applicati
        return response;
    }
}
