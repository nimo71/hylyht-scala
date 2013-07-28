package ocre.adapter.servlet.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface HttpResponse {

	void send(HttpServletResponse resp) throws IOException;

}
