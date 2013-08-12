package hylyht.adapter.servlet;

import com.google.common.base.Optional;
import hylyht.adapter.servlet.resource.Resource;
import hylyht.adapter.servlet.resource.ResourceLoader;
import hylyht.adapter.servlet.response.HttpResponse;
import hylyht.adapter.servlet.response.InternalServerError;
import hylyht.adapter.servlet.response.NotFound;
import hylyht.adapter.servlet.response.Ok;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoadStaticFile implements Action {

    private ResourceLoader loader;

    public LoadStaticFile(ResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public HttpResponse execute(HttpServletRequest req) {
        try {
            Optional<? extends Resource> optionalResource = loader.load(req.getPathInfo());
            if (optionalResource.isPresent()) {
                return new Ok(optionalResource.get());
            }
            return new NotFound();
        }
        catch (IOException e) {
            return new InternalServerError();
        }
    }
}
