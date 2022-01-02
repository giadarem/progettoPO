package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Service.ServiceStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

public class MinStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinStatistic.class);

    public MinStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) {
        //Converto il numero di post da Str a Int e setto il Min al valore in posizione 0
        ArrayList<Integer> all_list = new ServiceStatistics(list).StringToInt(list); float min = all_list.get(0);
        //Per ogni posizione verifico se il valore è più piccolo di Min - Se vero, diventa Min
        for(int i = 0; i < all_list.size(); i++) { if(all_list.get(i) < min) { min = all_list.get(i); } }
        LOGGER.info("*** REQUEST STATUS [\"statistics - MIN\"]- " + HttpStatus.OK + " ***");
        //Ritorno il numero minimo di post presenti nei Tweet visualizzati
        return min;
    }

}
