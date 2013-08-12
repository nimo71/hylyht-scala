package hylyht.adapter.servlet.resource;

import java.io.IOException;
import java.io.PrintWriter;

public interface Resource {

	void write(PrintWriter writer) throws IOException;

	int getContentLength();

	MimeType getMimeType();

}