package hylyht.adapter.servlet;

import hylyht.adapter.servlet.response.HttpResponse;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    HttpResponse execute(HttpServletRequest req);
}
