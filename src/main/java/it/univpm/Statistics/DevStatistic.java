package it.univpm.Statistics;

import it.univpm.Abstract.AbstractStatistic;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Service.ServiceStatistics;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;

public class DevStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(DevStatistic.class);

    public DevStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) throws IOException, ParseException {
        LOGGER.info("*** REQUEST STATUS [\"statistics - DEV\"]- " + HttpStatus.OK + " ***");
        //Ritorno il valore del calcolo della radice della varianza
        return (float) Math.sqrt(getVariance(new ServiceStatistics(list).StringToInt(list)));
    }

    //Calcolo della media
    public float getMean(ArrayList<Integer> list){
        float sum = 0;
        for(float f : list) { sum += f; }
        return (sum/list.size());
    }
    //Calcolo della varianza
    public float getVariance(ArrayList<Integer> list){
        float mean = getMean(list);
        float sum = 0;
        for(float f : list){ sum += (f-mean)*(f-mean); }
        return sum/(list.size()-1);
    }
}
