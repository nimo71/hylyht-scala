package webmarker.adapter.servlet.resource;

import java.io.*;

public class StaticResource implements Resource {
	private final String content;
	private final MimeType mimeType;

	public StaticResource(InputStream inputStream, MimeType mimeType) throws IOException {
		this.mimeType = mimeType;
		final StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(inputStream));
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				sb.append(line);
				sb.append("\n");
			}
		} 
		finally {
			if (in != null) in.close();
		}
		this.content = sb.substring(0, sb.length() - 1);
	}

	@Override
	public void write(PrintWriter writer) throws IOException 
	{
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(writer);
			out.write(content);
			out.flush();
		} 
		finally {
			if (out != null) out.close();
		}		
	}

	@Override
	public int getContentLength() {
		return content.length();
	}

	@Override
	public MimeType getMimeType() {
		return mimeType;
	}
	
}
