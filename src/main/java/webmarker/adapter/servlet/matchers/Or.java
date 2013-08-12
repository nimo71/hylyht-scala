package webmarker.adapter.servlet.matchers;

import javax.servlet.http.HttpServletRequest;

public class Or implements Matcher {

	private final Matcher[] matchers;

	public Or(Matcher... matchers) {
		this.matchers = matchers;
	}

	@Override
	public boolean matches(HttpServletRequest req) {
		for (Matcher matcher : matchers) {
			if (matcher.matches(req)) return true; 
		}
		return false;
	}

}
