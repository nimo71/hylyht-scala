package webmarker.adapter.servlet.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpResponse {

	void send(HttpServletResponse resp) throws IOException;

}
