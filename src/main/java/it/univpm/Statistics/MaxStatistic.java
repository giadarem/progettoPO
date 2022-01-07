package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Service.ServiceStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

/** 
 * calcola il numero massimo di post presenti nei Tweet visualizzati
 *
 */
public class MaxStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaxStatistic.class);

    public MaxStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) {
        //Converto il numero di post da Str a Int e setto il Max al valore in posizione 0
        ArrayList<Integer> all_list = new ServiceStatistics(list).StringToInt(list); float max = all_list.get(0);
        //Per ogni posizione verifico se il valore è più grande di Max - Se vero, diventa Max
        for(int i = 0; i < all_list.size(); i++) { if(all_list.get(i) > max) { max = all_list.get(i); } }
        LOGGER.info("*** REQUEST STATUS [\"statistics - MAX\"]- " + HttpStatus.OK + " ***");
        //Ritorno il numero massimo di post presenti nei Tweet visualizzati
        return max;
    }

}
