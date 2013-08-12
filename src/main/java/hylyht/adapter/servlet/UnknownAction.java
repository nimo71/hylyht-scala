package hylyht.adapter.servlet;

import hylyht.adapter.servlet.response.HttpResponse;
import hylyht.adapter.servlet.response.NotFound;

import javax.servlet.http.HttpServletRequest;

public class UnknownAction implements Action {
    private static final NotFound NOT_FOUND = new NotFound();

    @Override
    public HttpResponse execute(HttpServletRequest req) {
        return NOT_FOUND;
    }
}
