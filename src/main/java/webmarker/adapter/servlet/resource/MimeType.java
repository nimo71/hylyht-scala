package webmarker.adapter.servlet.resource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import webmarker.adapter.servlet.response.HttpResponse;

import javax.servlet.http.HttpServletResponse;

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
