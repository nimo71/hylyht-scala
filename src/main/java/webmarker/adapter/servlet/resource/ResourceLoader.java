package webmarker.adapter.servlet.resource;

import com.google.common.base.Optional;

import java.io.IOException;

public interface ResourceLoader {

	Optional<? extends Resource> load(String path) throws IOException;

}
