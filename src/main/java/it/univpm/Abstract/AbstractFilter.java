package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

//CLASSE ESTESA DA IncludedFilter, SearchFilter, LowerFilter, UpperFilter
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
    //Verifica la presenza del valore ricercato, all'interno del Tweet analizzato
    public abstract boolean searchElement(TweetPost elem);
    //Popola un array di appoggio con tutti i Tweet che soddisfano il filtro
    public abstract ArrayListTweetPost getFound(ArrayListTweetPost list);

}
