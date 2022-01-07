package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import org.json.simple.parser.ParseException;
import java.io.IOException;

//CLASSE ESTESA DA AvgStatistic, DevStatistic, FreqStatistic, MaxStatistic, MinStatistic, SumStatistic
<<<<<<< HEAD
=======

/**
 * classe astratta utilizzata per i filtri
 */
>>>>>>> 202cfeda42693eceeed05c1e40a98afe2d262ce8
public abstract class AbstractStatistic {
    public AbstractStatistic(){}

    //METODI ASTRATTI - DA IMPLEMENTARE NELLE CLASSI ESTESE
<<<<<<< HEAD
    //Calcolo per il recupero del valore max, min, avg, dev, sum sul numero dei post dell'utente e freq Tweets
=======
    /**
     * Calcolo per il recupero del valore max, min, avg, dev, sum sul numero dei post dell'utente e freq Tweets
     * @throws IOException
     * @throws ParseException
     */
>>>>>>> 202cfeda42693eceeed05c1e40a98afe2d262ce8
    public abstract float getValues(ArrayListTweetPost list) throws IOException, ParseException;
}
