package hylyht.adapter.servlet.resource;

import com.google.common.base.Optional;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class ServletResourceLoader implements ResourceLoader {

	private static final String DEFAULT_FILE_EXTENSION = "html";
	private final ServletContext servletContext;

	public ServletResourceLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public Optional<? extends Resource> load(String pathInfo) throws IOException {
        MimeType mime = new ExtensionToMimeType().apply(parseFileExtension(pathInfo));
        InputStream in = servletContext.getResourceAsStream(pathInfo);
        if (in == null) return Optional.absent();
        return Optional.of(new StaticResource(in, mime));
	}

	private String parseFileExtension(String pathInfo) {
		String[] parts = pathInfo.split("\\.");
		if (parts.length > 1) return parts[parts.length - 1]; 
		return DEFAULT_FILE_EXTENSION;
	}

}
