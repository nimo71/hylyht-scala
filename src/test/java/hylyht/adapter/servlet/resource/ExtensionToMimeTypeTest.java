package hylyht.adapter.servlet.resource;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExtensionToMimeTypeTest {

	@Test
	public void returnsTextHtmlMimeType() throws IOException {
		MimeType mimeType = new ExtensionToMimeType().apply("html");
		assertThat(mimeType, is(new MimeType("text/html")));
	}
	@Test
	public void returnsCssMimeType() throws IOException {
		MimeType mimeType = new ExtensionToMimeType().apply("css");
		assertThat(mimeType, is(new MimeType("text/css")));
	}
	@Test
	public void returnsApplicationJavascriptMimeType() throws IOException {
		MimeType mimeType = new ExtensionToMimeType().apply("js");
		assertThat(mimeType, is(new MimeType("application/javascript")));
	}
}
