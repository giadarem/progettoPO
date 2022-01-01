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

public class SumStatistic extends AbstractStatistic {
    private static final Logger LOGGER = LoggerFactory.getLogger(SumStatistic.class);

    public SumStatistic(){super();}

    @Override
    public float getValues(ArrayListTweetPost list) throws IOException, ParseException {
        float sum = 0; ArrayList<Integer> all_list = new ServiceStatistics(list).StringToInt(list);

        for(int i = 0; i < all_list.size(); i++) { sum += all_list.get(i); }

        LOGGER.info("*** REQUEST STATUS [\"statistics - SUM\"]- " + HttpStatus.OK + " ***");
        return sum;
    }
}
