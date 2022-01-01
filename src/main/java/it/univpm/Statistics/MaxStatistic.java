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

public class MaxStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaxStatistic.class);

    public MaxStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) throws IOException, ParseException {
        ArrayList<Integer> all_list = new ServiceStatistics(list).StringToInt(list); float max = all_list.get(0);

        for(int i = 0; i < all_list.size(); i++) { if(all_list.get(i) > max) { max = all_list.get(i); } }

        LOGGER.info("*** REQUEST STATUS [\"statistics - MAX\"]- " + HttpStatus.OK + " ***");
        return max;
    }

}
