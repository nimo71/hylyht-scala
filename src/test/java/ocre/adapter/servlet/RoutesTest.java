package ocre.adapter.servlet;

import java.io.*;

import javax.servlet.http.HttpServletRequest;

import ocre.adapter.servlet.matchers.PathInfo;
import ocre.adapter.servlet.resource.*;
import ocre.adapter.servlet.response.*;

import org.apache.commons.lang3.builder.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RoutesTest implements ResourceLoader {
	private static final String JS_FILENAME = "/js/filename.js";
	private static final String UNKNOWN_ROUTE = "/unknown/route";
	private static final String MATCHING_ROUTE = "/matching/route";
	private static final String SERVER_ERROR_ROUTE = "/server/error";
	
	@Mock
    private HttpServletRequest req;
	
	@Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void returnsNotFoundForUnknownRoute() throws IOException {
        when(req.getPathInfo()).thenReturn(UNKNOWN_ROUTE);
        HttpResponse httpResponse = new Routes(this).route(req);
        assertThat(httpResponse, is((HttpResponse)new NotFound()));
    }
    
    @Test 
    public void returnsOkResponseForMatchedRoute() throws IOException {
    	HttpResponse httpResponse = requestRoute(MATCHING_ROUTE);
    	assertThat(httpResponse, is((HttpResponse) new Ok(new MatchedResource(MATCHING_ROUTE))));
    }
    
    @Test
    public void returnsOkResponseForRegExMatchedRoute() throws IOException {
    	when(req.getPathInfo()).thenReturn(JS_FILENAME);
        Routes routes = new Routes(this);
        routes.add(new Route(new PathInfo(".*\\.js")));
		HttpResponse httpResponse = routes.route(req);
        assertThat(httpResponse, is((HttpResponse) new Ok(new MatchedResource(JS_FILENAME))));
    } 

    @Test
    public void returnsInternalServerErrorOnErrorLoadingResource() throws IOException {
    	HttpResponse httpResponse = requestRoute(SERVER_ERROR_ROUTE);
    	assertThat(httpResponse, is((HttpResponse) new InternalServerError()));
    }

    private HttpResponse requestRoute(String matchingRoute) throws IOException {
		when(req.getPathInfo()).thenReturn(matchingRoute);
    	
    	Routes routes = new Routes(this);
    	routes.add(new Route(new PathInfo(matchingRoute))); 
		HttpResponse httpResponse = routes.route(req);
		return httpResponse;
	}

	@Override
	public Resource load(String pathInfo) throws IOException {
		if (SERVER_ERROR_ROUTE.equals(pathInfo)) throw new IOException(); 
		return new MatchedResource(pathInfo);
	}
	
	private static class MatchedResource implements Resource { 
		@SuppressWarnings("unused")
		private final String path;
		
		public MatchedResource(String path) {
			this.path = path;
		}
		
		@Override
		public void write(PrintWriter writer) throws IOException {}
		
		@Override
		public int getContentLength() {	return 0; }   	
		
		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj, false);
		}
		
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, false);
		}

		@Override
		public MimeType getMimeType() {
			return new MimeType("text/html");
		}
	};
}
