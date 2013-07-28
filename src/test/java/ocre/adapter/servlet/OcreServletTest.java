package ocre.adapter.servlet;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OcreServletTest {
	private static final String INDEX_HTML = "/index.html";
	private static final String BOOKMARKLET_JS = "/bookmarklet.js";

	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse resp;
	
	@Mock
	private PrintWriter responseWriter;

	@Mock
	private ServletConfig config;

	@Mock
	private ServletContext servletContext;

	private OcreServlet ocreServlet = new OcreServlet();
	private FileInputStream indexIn;
	private FileInputStream bookmarkletIn;
	
	@Before
	public void beforeEachTest() throws IOException, ServletException {
		MockitoAnnotations.initMocks(this);
		
		when(resp.getWriter()).thenReturn(responseWriter);
		when(config.getServletContext()).thenReturn(servletContext);
		
		indexIn = new FileInputStream("./src/main/webapp/index.html");
		bookmarkletIn = new FileInputStream("./src/main/webapp/bookmarklet.js");
		when(servletContext.getResourceAsStream(INDEX_HTML)).thenReturn(indexIn);
		when(servletContext.getResourceAsStream(BOOKMARKLET_JS)).thenReturn(bookmarkletIn);
		
		ocreServlet.init(config);
	}
	
	@After
	public void afterEachTest() {
		try {
			indexIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bookmarkletIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getsIndexPage() throws ServletException, IOException {
		when(req.getPathInfo()).thenReturn(INDEX_HTML);
		ocreServlet.service(req, resp);
		verify(resp).setStatus(HttpServletResponse.SC_OK);
		verify(resp).setContentType("text/html");
		verify(resp).setContentLength(anyInt());
		verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
		
		ArgumentCaptor<char[]> argument = ArgumentCaptor.forClass(char[].class);
		verify(responseWriter).write(argument.capture(), anyInt(), anyInt());
		assertThat(new String(argument.getValue()), containsString("<script src=\"js/main.js\"></script>")); 
	}
	
	@Test
	public void respondsWithNotFound() throws ServletException, IOException {
		when(req.getPathInfo()).thenReturn("unknown/path.html");
		ocreServlet.service(req, resp);
		verify(resp).setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@Test
	public void getsBookmarkletJs() throws ServletException, IOException {
		when(req.getPathInfo()).thenReturn(BOOKMARKLET_JS);
		ocreServlet.service(req, resp);
		verify(resp).setStatus(HttpServletResponse.SC_OK);
		verify(resp).setContentType("application/javascript");
		verify(resp).setContentLength(anyInt());
	}
	
}
