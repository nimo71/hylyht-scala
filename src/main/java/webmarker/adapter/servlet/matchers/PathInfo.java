package webmarker.adapter.servlet.matchers;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

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
