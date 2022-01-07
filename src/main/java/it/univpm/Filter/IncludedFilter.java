package it.univpm.Filter;

import it.univpm.Abstract.AbstractFilter;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

/** 
 * classe che implementa il filtro "essere incluso tra due parametri"
 * @param search_value, rappresenta il valore che deve essere incluso tra i due parametri
 *
 */
public class IncludedFilter extends AbstractFilter {
    private String search_value = "";

    public IncludedFilter(String search_field, String search_value){
        super(search_field);
        this.setSearch_value(search_value);
    }

    //GETTER & SETTER
    public String getSearch_value() { return search_value; }
    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    /**
     * verifica se all'interno dei Tweet esiste un valore intero compreso tra i valori forniti 
     * 
     * @return true se trova il valore, false se non lo trova
     */
    @Override
    public boolean searchElement(TweetPost elem) {
        boolean check = false;
        //Recupero i due estremi
        String[] value = this.getSearch_value().split("-");
        if (value.length == 2) {
            //Switch sul nome del campo fornito
            switch (getFields()) {
                case "post_num":
                    //Se trovo un valore intero all'interno di ogni Tweet, compreso tra i valori forniti, esco con true
                    if (Integer.parseInt(elem.getUser_post_num()) >= Integer.parseInt(value[0]) && Integer.parseInt(elem.getUser_post_num()) <= Integer.parseInt(value[1]))
                        check = true;
                    break;
            }
        }
        return check;
    }
    /**
     * aggiunge un Tweet all'array solo se il Tweet presenta un valore 
     * incluso tra i due valori forniti all'interno dei Tweet
     * 
     * @return l'array contenente i tweet
     */
    @Override
    public ArrayListTweetPost getFound(ArrayListTweetPost list) {
        ArrayListTweetPost array = new ArrayListTweetPost();
        for(int i = 0; i < list.getAllTweets().size(); i++)
        {
            //Verifico la presenza di un valore incluso tra i valori forniti, all'interno dei Tweet
            if(searchElement(list.getElementByID(i)))
                //Se esiste aggiunto il Tweet all'array
                array.addElement(list.getElementByID(i));
        }
        return array;
    }
}
