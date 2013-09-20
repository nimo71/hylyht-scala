package hylyht.adapter.ws.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StaticContent implements Content {
	private final String content;

	public StaticContent(InputStream inputStream) throws IOException {
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
    public String getContentString() {
        return content;
    }
	
}
