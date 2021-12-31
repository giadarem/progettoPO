package it.univpm.Statistics;
import it.univpm.ArrayLists.ArrayListDate;

public class TimeStatistic {
	public String num;
	public TimeStatistic(String num) {this.num=num;}
	// castare il numero
	private int numero=Integer.parseInt(num);
	
	String[] date_split = new String[6];
	String[] time_split = new String[3];
	int hours[] = new int [15] ;
	int minutes[] = new int [15];
	int seconds[] = new int [15];
	int appoggio[]=new int [15];
	
	ArrayListDate prova = new ArrayListDate();
	
	public ArrayListDate getFound(ArrayListDate list) {
        ArrayListDate array = new ArrayListDate();
        for(int i = 0; i < list.getAllDate().size(); i++)
        {	System.out.println("ciao imbecile");
        	//Conversione data in numero double 
        	String s = String.valueOf(list.getElementByID(i));
        	date_split =s.split(" ");
        	time_split = date_split[3].split(":");
        	hours[i] = Integer.parseInt(time_split[0]);
        	minutes[i]=Integer.parseInt(time_split[1]);
        	seconds[i]=Integer.parseInt(time_split[2]);
        	appoggio[i]=seconds[i]+minutes[i]*60+hours[i]*60*60;
        	// trova quanti tweet negli ultimi 30 secondi  FORSE DA CAMBIARE ORDINE, NON SO SE CRESCENTE O DECRESCENTE 
        	if(appoggio[i]-appoggio[14]<=numero){array.addElement(list.getElementByID(i));}// aggiungi la data alla lista 
        	
        	// differenza tra tempo 0 appoggio 15 o viceversa 
        	double tempo=appoggio[0]-appoggio[15];
        }
        return array;
    }
	
}

	/*
	private boolean searchElement(TweetDate elementByID) {
		return false;
	}	
}
	/*
	private ArraylistTweet;
	 public boolean searchElement(TweetPost elem) {
	        switch(getFields())
	        {
	            case "hashtags":
	                String[] str_hashtags = elem.getPost_hashtags();
	                for(int i = 0; i < str_hashtags.length; i++)
	                {
	                    if(str_hashtags[i].contains(this.getSearch_value())){ return true; }
	                }
	                return false;
	            case "username":
	                System.out.println("ciao");
	                if(elem.getUser_name().contains(this.getSearch_value()))
	                    return true;
	                return false;
	            case "mentions":
	                String[] str = elem.getUser_post_mentions();
	                for(int i = 0; i < str.length; i++)
	                {
	                    if(str[i].contains(this.getSearch_value())){ return true; }
	                }
	                return false;
	        }
	
}
*/