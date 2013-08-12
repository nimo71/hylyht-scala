package webmarker.adapter.servlet;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
 * Define a route class 
 * Route is a matcher for the request
 * Iterate over the request 
 * The first route matching the request is used to service the request
 * Resource loader loads the request for the matching route 
 */
public class Routes {
    private static final UnknownAction UNKNOWN_ACTION = new UnknownAction();

    private static Logger logger = LoggerFactory.getLogger(Routes.class);
	private final Map<Route, Action> routeMap = Maps.newHashMap();

	public Action route(HttpServletRequest req) {
		for (Route route : routeMap.keySet()) {
            if (route.matches(req)) {
                Action action = routeMap.get(route);
                if (logger.isInfoEnabled()) logger.info("route to action: "+ action);
                return action;
            }
        }
        return UNKNOWN_ACTION;
	}

	public Routes add(Route route, Action action) {
		routeMap.put(route, action);
        return this;
	}
}
