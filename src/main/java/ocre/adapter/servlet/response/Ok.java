package ocre.adapter.servlet.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import ocre.adapter.servlet.resource.Resource;

import org.apache.commons.lang3.builder.*;

import static javax.servlet.http.HttpServletResponse.SC_OK;

public class Ok implements HttpResponse {

	private final Resource resource;

	public Ok(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void send(HttpServletResponse resp) throws IOException {
		resource.getMimeType().send(resp);
		resource.write(resp.getWriter());
		resp.setContentLength(resource.getContentLength());
		resp.setStatus(SC_OK);
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
