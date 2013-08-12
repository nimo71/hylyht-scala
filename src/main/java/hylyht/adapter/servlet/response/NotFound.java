package hylyht.adapter.servlet.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.servlet.http.HttpServletResponse;

public class NotFound implements HttpResponse {

	private final int responseCode = HttpServletResponse.SC_NOT_FOUND;
	
	@Override
	public void send(HttpServletResponse resp) {
		resp.setStatus(responseCode);
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