package webmarker.adapter.servlet;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WebMarkerServletIT {
	
	@Test
	public void respondsSuccessForIndex() throws ClientProtocolException, IOException {
		Response response = Request.Get("http://localhost:8080/webmarker").execute();
		assertThat(response.returnResponse().getStatusLine().getStatusCode(), is(200)); 
	}

}
