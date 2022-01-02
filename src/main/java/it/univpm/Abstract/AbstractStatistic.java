package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import org.json.simple.parser.ParseException;
import java.io.IOException;

//CLASSE ESTESA DA AvgStatistic, DevStatistic, FreqStatistic, MaxStatistic, MinStatistic, SumStatistic
public abstract class AbstractStatistic {
    public AbstractStatistic(){}

    //METODI ASTRATTI - DA IMPLEMENTARE NELLE CLASSI ESTESE
    //Calcolo per il recupero del valore max, min, avg, dev, sum sul numero dei post dell'utente e freq Tweets
    public abstract float getValues(ArrayListTweetPost list) throws IOException, ParseException;
}
