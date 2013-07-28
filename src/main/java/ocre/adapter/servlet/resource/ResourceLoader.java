package ocre.adapter.servlet.resource;

import java.io.IOException;

public interface ResourceLoader {

	Resource load(String path) throws IOException;

}
