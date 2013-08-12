package webmarker.adapter.servlet.resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ServletResourceLoaderTest {

    @Mock
    private ServletContext servletContext;

    @Before
    public void beforeEachTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnsAbsentWhenResourcNotFound() throws IOException {
        String path = "unknownPath";
        when(servletContext.getResourcePaths(path)).thenReturn(null);
        assertThat(new ServletResourceLoader(servletContext).load(path).isPresent(), is(false));
    }
}
