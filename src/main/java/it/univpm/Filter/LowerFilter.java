package it.univpm.Filter;

import it.univpm.Abstract.AbstractFilter;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

/**
 * classe che implementa il filtro "essere minore di un certo valorei"
 * @param search_value, rappresenta il valore su cui fare la verifica
 *
 */
public class LowerFilter extends AbstractFilter {
    private String search_value = "";

    public LowerFilter(String search_field, String search_value){
        super(search_field);
        this.setSearch_value(search_value);
    }

    //GETTER & SETTER
    public String getSearch_value() {
        return search_value;
    }
    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }
    
    /**
     * verifica se all'interno di ogni Tweet esiste un valore minore del valore fornito
     * 
     * @return true se trova il valore, false se non lo trova
     */
    @Override
    public boolean searchElement(TweetPost elem) {
        boolean check = false;
        //Switch sul nome del campo fornito
        switch(getFields())
        {
            case "post_num":
                //Se trovo un valore intero all'interno di ogni Tweet, minore del valore fornito, esco con true
                if(Integer.parseInt(elem.getUser_post_num()) <= Integer.parseInt(this.getSearch_value())) check = true;
                break;
        }
        return check;
    }
    
    /**
     * aggiunge un Tweet all'array solo se il Tweet presenta un valore 
     * minore del valore fornitp
     * 
     * @return l'array contenente i tweet
     */
    @Override
    public ArrayListTweetPost getFound(ArrayListTweetPost list) {
        ArrayListTweetPost array = new ArrayListTweetPost();
        for(int i = 0; i < list.getAllTweets().size(); i++)
        {
            //Verifico la presenza di un valore minore all'interno dei Tweet rispeto a quello fornito
            if(searchElement(list.getElementByID(i)))
                //Se esiste aggiunto il Tweet all'array
                array.addElement(list.getElementByID(i));
        }
        return array;
    }
}
