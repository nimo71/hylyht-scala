package ocre.adapter.servlet;

import javax.servlet.http.HttpServletRequest;

import ocre.adapter.servlet.matchers.Matcher;

public class Route {
	private final Matcher[] matchers;

	public Route(Matcher... matchers) {
		this(".*", matchers);
	}
	
	public Route(String resourcePath, Matcher... matchers) {
		this.matchers = matchers;
	}

	public boolean matches(HttpServletRequest req) {
		for (Matcher matcher : matchers) {
			if (!matcher.matches(req)) return false;
		}
		return true;
	}
}