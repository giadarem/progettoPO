package it.univpm.SpringBootApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Model.TweetPost;
import it.univpm.Statistics.*;


/** E' la classe test che controlla il corretto funzionamento delle statistiche
 */
public class TestStatistic {
	// creo oggetto tweet post della classe tweetpost
	ArrayListTweetPost arrayList;
	
	double max;double min;double avg;double dev;double freq;double sum;
	@BeforeEach
	void setUp() throws Exception {
	
	arrayList= new ArrayListTweetPost();
	
	TweetPost t1 = new TweetPost ("Thu Jan 13 22:53:16 +0100 2022","1","1" ,"nicopiccia","1", "it", "recent","Milano",null,null) ;
	TweetPost t2 = new TweetPost ("Thu Jan 13 20:53:16 +0100 2022","2","2" ,"giadarem","2", "en", "recent","Ancona",null,null) ;
	TweetPost t3 = new TweetPost ("Thu Jan 13 19:53:16 +0100 2022","3","3" ,"java","1", "it", "recent","Roma",null,null) ;
	TweetPost t4 = new TweetPost ("Thu Jan 13 18:53:16 +0100 2022","4","4" ,"ludopatia","1", "it", "mixed","Napoli",null,null) ;
	
	arrayList.addElement(t1);
	arrayList.addElement(t2);
	arrayList.addElement(t3);
	arrayList.addElement(t4);

	max = new MaxStatistic().getValues(arrayList);
	sum =  new SumStatistic().getValues(arrayList);
	min = new MinStatistic().getValues(arrayList);
	avg = new AvgStatistic().getValues(arrayList);
	dev = new DevStatistic().getValues(arrayList);
	freq= new FreqStatistic().getValues(arrayList);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals(2.0,max);
		assertEquals(5,sum);
		assertEquals(1,min);
		assertEquals(1.25,avg);
		assertEquals(0.5,dev);
		assertEquals(3600,freq);
	}
}