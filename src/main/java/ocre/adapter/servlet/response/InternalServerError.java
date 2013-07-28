package ocre.adapter.servlet.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.*;

public class InternalServerError implements HttpResponse {

	@Override
	public void send(HttpServletResponse resp) throws IOException {
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

}
