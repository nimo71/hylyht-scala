package hylyht.adapter.servlet.resource

import hylyht.adapter.servlet.ContentLoader
import javax.servlet.ServletContext

class ServletContentLoader(
        private val servletContext: ServletContext)
    extends ContentLoader
{

    def load(pathInfo: String): Option[Content] = {
        val in = servletContext.getResourceAsStream(pathInfo)
        if (in == null)
            None
        else
            Some(new StaticContent(in))
    }

}