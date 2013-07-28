package ocre.adapter.servlet.resource;

import java.io.IOException;

import javax.servlet.ServletContext;

public class ServletResourceLoader implements ResourceLoader {

	private static final String DEFAULT_FILE_EXTENSION = "html";
	private final ServletContext servletContext;

	public ServletResourceLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public Resource load(String pathInfo) throws IOException {
		MimeType mime = new ExtensionToMimeType().apply(parseFileExtension(pathInfo)); 
		return new StaticResource(servletContext.getResourceAsStream(pathInfo), mime);
	}

	private String parseFileExtension(String pathInfo) {
		String[] parts = pathInfo.split("\\.");
		if (parts.length > 1) return parts[parts.length - 1]; 
		return DEFAULT_FILE_EXTENSION;
	}

}
