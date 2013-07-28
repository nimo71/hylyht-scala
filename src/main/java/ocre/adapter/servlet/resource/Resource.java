package ocre.adapter.servlet.resource;

import java.io.*;

public interface Resource {

	void write(PrintWriter writer) throws IOException;

	int getContentLength();

	MimeType getMimeType();

}