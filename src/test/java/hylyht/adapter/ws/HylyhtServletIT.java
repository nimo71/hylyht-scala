package hylyht.adapter.ws;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HylyhtServletIT {
	
	@Test
	public void respondsSuccessForIndex() throws ClientProtocolException, IOException {
		Response response = Request.Get("http://localhost:8080/hylyht").execute();
		assertThat(response.returnResponse().getStatusLine().getStatusCode(), is(200)); 
	}

}
