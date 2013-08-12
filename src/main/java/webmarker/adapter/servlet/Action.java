package webmarker.adapter.servlet;

import webmarker.adapter.servlet.response.HttpResponse;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    HttpResponse execute(HttpServletRequest req);
}
