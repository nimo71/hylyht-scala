package hylyht.adapter.servlet

import org.junit.Before
import org.junit.Test
import org.mockito.{Mock, MockitoAnnotations}
import javax.servlet.ServletContext
import org.hamcrest.Matchers.is
import org.junit.Assert.assertThat
import org.mockito.Mockito.when
import org.scalatest.junit.AssertionsForJUnit
import hylyht.adapter.io.ServletContentLoader

class ServletResourceLoaderTest extends AssertionsForJUnit {

    @Mock private var servletContext: ServletContext = _

    @Before def beforeEachTest {
        MockitoAnnotations.initMocks(this)
    }

    @Test def returnsAbsentWhenResourcNotFound {
        val path: String = "unknownPath"
        when(servletContext.getResourcePaths(path)).thenReturn(null)
        assertThat(new ServletContentLoader(servletContext).load(path).isDefined, is(false))
    }

}
