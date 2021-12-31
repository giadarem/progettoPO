package it.univpm.Filter;

import it.univpm.Abstract.AbstractFilter;
import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

public class LowerFilter extends AbstractFilter {
    private String search_value = "";

    public LowerFilter(String search_field, String search_value){
        super(search_field);
        this.setSearch_value(search_value);
    }

    public String getSearch_value() {
        return search_value;
    }

    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    @Override
    public boolean searchElement(TweetPost elem) {
        boolean check = false;
        switch(getFields())
        {
            case "post_num":
                if(Integer.parseInt(elem.getUser_post_num()) <= Integer.parseInt(this.getSearch_value())) check = true;
                break;
        }
        return check;
    }

    @Override
    public ArrayListTweetPost getFound(ArrayListTweetPost list) {
        ArrayListTweetPost array = new ArrayListTweetPost();
        for(int i = 0; i < list.getAllTweets().size(); i++)
        {
            if(searchElement(list.getElementByID(i)))
                array.addElement(list.getElementByID(i));
        }
        return array;
    }
}
