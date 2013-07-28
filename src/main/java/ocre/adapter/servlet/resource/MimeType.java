package ocre.adapter.servlet.resource;

import javax.servlet.http.HttpServletResponse;

import ocre.adapter.servlet.response.HttpResponse;

import org.apache.commons.lang3.builder.*;

public class MimeType implements HttpResponse {
	private final String value;

	public MimeType(String value) {
		this.value = value;
	}

	@Override
	public void send(HttpServletResponse resp) {
		resp.setContentType(value);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
