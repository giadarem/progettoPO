package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Service.ServiceStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

public class SumStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(SumStatistic.class);

    public SumStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) {
        //Converto il numero di post da Str a Int
        float sum = 0; ArrayList<Integer> all_list = new ServiceStatistics(list).StringToInt(list);
        //Per ogni posizione sommo il valore trovato al valore precedente
        for(int i = 0; i < all_list.size(); i++) { sum += all_list.get(i); }
        LOGGER.info("*** REQUEST STATUS [\"statistics - SUM\"]- " + HttpStatus.OK + " ***");
        //Ritorno il numero totale di post presenti nei Tweet visualizzati
        return sum;
    }
}
