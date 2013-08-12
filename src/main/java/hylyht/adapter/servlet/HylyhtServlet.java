package hylyht.adapter.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hylyht.adapter.servlet.matchers.PathInfo;
import hylyht.adapter.servlet.resource.ServletResourceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HylyhtServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(HylyhtServlet.class);

	private static final String DEFAULT = "/";
	private static final String INDEX_HTML = "/index.html";
	private static final String JS_FILE_REGEX = ".*\\.js";
	private static final String CSS_FILE_REGEX = ".*\\.css";
    private static final String HTML_FILE_REGEX = ".*\\.html";

    private final Routes routes = new Routes();

	@Override
	public void init() throws ServletException  {

        LoadStaticFile loadStaticFile = new LoadStaticFile(new ServletResourceLoader(getServletContext()));

        routes.add(new Route(INDEX_HTML, new PathInfo(DEFAULT)), loadStaticFile)
            .add(new Route(new PathInfo(JS_FILE_REGEX)), loadStaticFile)
            .add(new Route(new PathInfo(CSS_FILE_REGEX)), loadStaticFile)
            .add(new Route(new PathInfo(HTML_FILE_REGEX)), loadStaticFile);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        routes.route(req)
            .execute(req)
            .send(resp);
	}
}
