package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * calcola la statistica "ogni quanto tempo avviene un tweet"
 *
 */
public class FreqStatistic implements AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(FreqStatistic.class);

    public FreqStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list){
        //Preparazione variabili per conversione orario in secondi
        int[] array_seconds = new int [list.getAllTweets().size()];
        String[] date_split = new String[6];
        String[] time_split = new String[3];
        float frequency = 0;

        for(int list_count = 0; list_count < list.getAllTweets().size(); list_count++)
        {
            //Recupero la data di ogni Tweet
            String date_str = list.getElementByID(list_count).getPostDate();
            date_split = date_str.split(" ");
            //Ottengo l'orario
            time_split = date_split[3].split(":");
            //Convero l'orario in secondi
            array_seconds[list_count] = Integer.parseInt(time_split[2])+Integer.parseInt(time_split[1])*60+Integer.parseInt(time_split[0])*3600;

            //Se è l'ultima esecuzione calcolo la frequenza
            if((list_count+1) == list.getAllTweets().size()) { frequency = (array_seconds[0] - array_seconds[list_count])/list.getAllTweets().size(); }
        }

        LOGGER.info("*** REQUEST STATUS [\"statistics - FREQUENCY\"]- " + HttpStatus.OK + " ***");
        return frequency;
    }
}
