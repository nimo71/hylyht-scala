package hylyht.adapter.servlet;

import hylyht.adapter.servlet.matchers.Matcher;

import javax.servlet.http.HttpServletRequest;

public class Route {
	private final Matcher[] matchers;
	
	public Route(Matcher... matchers) {
		this.matchers = matchers;
	}

	public boolean matches(HttpServletRequest req) {
		for (Matcher matcher : matchers) {
			if (!matcher.matches(req)) return false;
		}
		return true;
	}
}