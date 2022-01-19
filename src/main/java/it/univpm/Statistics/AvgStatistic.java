package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.io.IOException;

/**
 * calcola la media
 *
 */
public class AvgStatistic implements AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(AvgStatistic.class);

    public AvgStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) throws IOException, ParseException {
        LOGGER.info("*** REQUEST STATUS [\"statistics - AVG\"]- " + HttpStatus.OK + " ***");
        //Utilizzando la classe della sommatoria ritorno la media utilizzando la dimensione della lista fornita
        return (new SumStatistic().getValues(list)/list.getAllTweets().size());
    }
}
