package hylyht.adapter.servlet.resource;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class ExtensionToMimeType {

	private final Map<String, MimeType> mimeTypes = Maps.newHashMap(); 
	
	public ExtensionToMimeType() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader( 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("mime.types")));
		
		for (String line = in.readLine(); line != null; line = in.readLine()) {
			parseIntoMap(line);
		}
	}
	
	private void parseIntoMap(String line) {
		if (commented(line)) return;
		String[] parts = line.split("[ \t]+");
		String[] extensions = Arrays.copyOfRange(parts, 1, parts.length);
		for (String extension : extensions) {
			mimeTypes.put(extension, new MimeType(parts[0]));
		}
 	}

	private boolean commented(String line) {
		return '#' == line.charAt(0);
	}

	public MimeType apply(String extension) {
		return mimeTypes.get(extension);
	}

}
