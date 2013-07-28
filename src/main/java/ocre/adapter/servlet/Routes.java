package ocre.adapter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ocre.adapter.servlet.resource.ResourceLoader;
import ocre.adapter.servlet.response.*;
import static com.google.common.collect.Lists.newArrayList;

/*
 * Define a route class 
 * Route is a matcher for the request
 * Iterate over the request 
 * The first route matching the request is used to service the request
 * Resource loader loads the request for the matching route 
 */
public class Routes {
	
	private final List<Route> routeList = newArrayList();
	private final ResourceLoader resourceLoader;
	
	public Routes(ResourceLoader resourceLoader) throws IOException {
		this.resourceLoader = resourceLoader;
	}

	public HttpResponse route(HttpServletRequest req) {
		String pathInfo = req.getPathInfo();
		
		try {
			if (routeMatches(req)) {
				return new Ok(resourceLoader.load(pathInfo));
			} 
			else {
				return new NotFound();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			return new InternalServerError();
		}
		
	}

	private boolean routeMatches(HttpServletRequest req) {
		for (Route route : routeList) {
			if (route.matches(req)) return true; 
		}
		return false;
	}

	public void add(Route route) {
		routeList.add(route);
	}
}
