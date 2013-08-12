package webmarker.adapter.servlet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webmarker.adapter.servlet.matchers.PathInfo;
import webmarker.adapter.servlet.resource.MimeType;
import webmarker.adapter.servlet.resource.Resource;
import webmarker.adapter.servlet.response.HttpResponse;
import webmarker.adapter.servlet.response.NotFound;
import webmarker.adapter.servlet.response.Ok;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RoutesTest {
	private static final String JS_FILENAME = "/js/filename.js";
	private static final String UNKNOWN_ROUTE = "/unknown/route";
	private static final String MATCHING_ROUTE = "/matching/route";
    private static final TestAction MATCHING_ACTION = new TestAction(new Ok(new MatchedResource(MATCHING_ROUTE)));

	@Mock
    private HttpServletRequest req;


    @Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void returnsNotFoundForUnknownRoute() throws IOException {
        when(req.getPathInfo()).thenReturn(UNKNOWN_ROUTE);
        Action action = new Routes().route(req);
        assertThat(action.execute(req), is((HttpResponse)new NotFound()));
    }
    
    @Test 
    public void returnsOkResponseForMatchedRoute() throws IOException {
        when(req.getPathInfo()).thenReturn(MATCHING_ROUTE);
        Routes routes = new Routes();
        routes.add(new Route(new PathInfo(MATCHING_ROUTE)), MATCHING_ACTION);
        assertThat(routes.route(req), sameInstance((Action) MATCHING_ACTION));
    }
    
    @Test
    public void returnsOkResponseForRegExMatchedRoute() throws IOException {
    	when(req.getPathInfo()).thenReturn(JS_FILENAME);
        Routes routes = new Routes();
        TestAction testAction = new TestAction(new Ok(new MatchedResource(JS_FILENAME)));
        routes.add(new Route(new PathInfo(".*\\.js")), testAction);

        assertThat(routes.route(req), sameInstance((Action) testAction));
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


    private static class TestAction implements Action {
        private HttpResponse res;

        public TestAction(HttpResponse res) {
            this.res = res;
        }

        @Override
        public HttpResponse execute(HttpServletRequest req) {
            return res;
        }
    };
}
