package ocre.adapter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ocre.adapter.servlet.matchers.*;
import ocre.adapter.servlet.resource.ServletResourceLoader;
import ocre.adapter.servlet.response.HttpResponse;

public class OcreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT = "/";
	private static final String INDEX_HTML = "/index.html";
	private static final String JS_FILE_REGEX = ".*\\.js";
	private static final String CSS_FILE_REGEX = ".*\\.css";

	private Routes routes;
	
	@Override
	public void init() throws ServletException  {
		try {
			routes = new Routes(new ServletResourceLoader(getServletContext()));
			routes.add(new Route(INDEX_HTML, new Or(new PathInfo(DEFAULT), new PathInfo(INDEX_HTML))));
			routes.add(new Route(new PathInfo(JS_FILE_REGEX)));
			routes.add(new Route(new PathInfo(CSS_FILE_REGEX)));
		} 
		catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpResponse response = routes.route(req);
		response.send(resp);
	}
}
