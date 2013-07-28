package ocre.adapter.servlet.resource;

import java.io.*;
import java.util.*;

import com.google.common.collect.Maps;

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
