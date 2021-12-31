package it.univpm.Model;
// per i tweet prendendo solo la data per le statistiche 


public class TweetDate {
	private String post_date;
 

    //COSTRUTTORI
    public TweetDate(){
        this.post_date = "";
    }

    public TweetDate(String postDate){
        setPostDate(postDate);
    }

    public TweetDate(TweetDate td){
        setPostDate(td.getPostDate());
       
    }

    //GETTER & SETTER
    public String getPostDate() {return post_date;}
    public void setPostDate(String post_id) {this.post_date = post_date;}

}
