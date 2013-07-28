package ocre.adapter.servlet.matchers;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class PathInfo implements Matcher {

	private final Pattern pathRegEx;

	public PathInfo(String pathRegEx) {
		this.pathRegEx = Pattern.compile(pathRegEx);
	}

	@Override
	public boolean matches(HttpServletRequest req) {
		return pathRegEx.matcher(req.getPathInfo()).matches();
	}

}
