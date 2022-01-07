package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

//CLASSE ESTESA DA IncludedFilter, SearchFilter, LowerFilter, UpperFilter
<<<<<<< HEAD
=======

/**
 * classe astratta utilizzata per i filtri
 */
>>>>>>> 202cfeda42693eceeed05c1e40a98afe2d262ce8
public abstract class AbstractFilter {
    protected String search_fields; //Campo di ricerca

    public AbstractFilter(String search_fields) {
        setFields(search_fields);
    }

    //GETTER & SETTER
    public String getFields() {
        return search_fields;
    }
    public void setFields(String search_fields) {
        this.search_fields = search_fields;
    }

    //METODI ASTRATTI - DA IMPLEMENTARE NELLE CLASSI ESTESE
<<<<<<< HEAD
    //Verifica la presenza del valore ricercato, all'interno del Tweet analizzato
    public abstract boolean searchElement(TweetPost elem);
    //Popola un array di appoggio con tutti i Tweet che soddisfano il filtro
=======
    /**
     * Verifica la presenza del valore ricercato, all'interno del Tweet analizzato
     */
    public abstract boolean searchElement(TweetPost elem);
    /**
     * Popola un array di appoggio con tutti i Tweet che soddisfano il filtro
     */
>>>>>>> 202cfeda42693eceeed05c1e40a98afe2d262ce8
    public abstract ArrayListTweetPost getFound(ArrayListTweetPost list);

}
