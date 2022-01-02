package it.univpm.Filter;

import it.univpm.Abstract.AbstractFilter;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

public class SearchFilter extends AbstractFilter{
    private String search_value = "";

    public SearchFilter(String search_field, String search_value){
        super(search_field);
        this.setSearch_value(search_value);
    }

    //GETTER & SETTER
    public String getSearch_value() { return search_value; }
    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    @Override
    public boolean searchElement(TweetPost elem) {
        boolean check = false;
        //Switch sul nome del campo fornito
        switch(getFields())
        {
            case "hashtags":
                String[] str_hashtags = elem.getPost_hashtags();
                for(int i = 0; i < str_hashtags.length; i++)
                {
                    //Se il valore da ricercare è contenuto nell'elemento fornito esco con true
                    if(str_hashtags[i].contains(this.getSearch_value())){ check = true; }
                }
                break;
            case "username":
                //Se il valore da ricercare è contenuto nell'elemento fornito esco con true
                if(elem.getUser_name().contains(this.getSearch_value()))
                    check = true;
                break;
            case "mentions":
                String[] str = elem.getUser_post_mentions();
                for(int i = 0; i < str.length; i++)
                {
                    //Se il valore da ricercare è contenuto nell'elemento fornito esco con true
                    if(str[i].contains(this.getSearch_value())){ check = true; }
                }
                break;
        }
        //Esco con false se non ho un filtro valido o se non si trovano corrispondenze
        return check;
    }

    @Override
    public ArrayListTweetPost getFound(ArrayListTweetPost list) {
        ArrayListTweetPost array = new ArrayListTweetPost();
        for(int i = 0; i < list.getAllTweets().size(); i++)
        {
            //Verifico la presenza del valore da ricercare all'interno di ogni singolo Tweet
            if(searchElement(list.getElementByID(i)))
                //Se presente, aggiungo all'array di appoggio
                array.addElement(list.getElementByID(i));
        }
        return array;
    }
}
