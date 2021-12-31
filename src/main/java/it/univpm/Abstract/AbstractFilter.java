package it.univpm.Abstract;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;

public abstract class AbstractFilter {
    protected String search_fields;

    public AbstractFilter(String search_fields) {
        setFields(search_fields);
    }

    public String getFields() {
        return search_fields;
    }
    public void setFields(String search_fields) {
        this.search_fields = search_fields;
    }

    public abstract boolean searchElement(TweetPost elem);
    public abstract ArrayListTweetPost getFound(ArrayListTweetPost list);

}
