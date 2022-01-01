package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public abstract class AbstractStatistic {
    public AbstractStatistic(){}

    public abstract float getValues(ArrayListTweetPost list) throws IOException, ParseException;
}
