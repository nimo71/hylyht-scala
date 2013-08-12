package webmarker.adapter.servlet.matchers;

import javax.servlet.http.HttpServletRequest;

public interface Matcher {
	boolean matches(HttpServletRequest req);
}