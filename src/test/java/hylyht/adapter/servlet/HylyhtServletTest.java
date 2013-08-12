package hylyht.adapter.servlet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HylyhtServletTest {
	private static final String INDEX_HTML = "/index.html";
	private static final String BOOKMARKLET_JS = "/bookmarklet.js";

	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse resp;

    @Before
	public void beforeEachTest() throws IOException, ServletException {
		MockitoAnnotations.initMocks(this);
	}

    @Ignore
    @Test
    public void todoInjectionToEnableTesting() {
        // either read routes in from servlet context and mock get context
        // or use dependency injection
    }
}
