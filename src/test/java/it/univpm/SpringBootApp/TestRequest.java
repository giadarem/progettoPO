package it.univpm.SpringBootApp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* Classe di Test che verifica la generazione
* corretta del codice HttpStatus.
*/

class TestRequest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( "http://localhost:8080/api/get-tweets" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		assertEquals(
			      httpResponse.getStatusLine().getStatusCode(),(HttpStatus.SC_OK));
	}

}
