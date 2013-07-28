package ocre.adapter.servlet;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OcreServletIT {
	
	@Test
	public void respondsSuccessForIndex() throws ClientProtocolException, IOException {
		Response response = Request.Get("http://localhost:8080/ocre").execute();
		assertThat(response.returnResponse().getStatusLine().getStatusCode(), is(200)); 
	}

}
